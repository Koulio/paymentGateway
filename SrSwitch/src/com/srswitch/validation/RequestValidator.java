package com.srswitch.validation;

import java.security.SecureRandom;
import java.util.UUID;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.ConsequenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.payment.model.DeviceProfileType;
import com.payment.model.SrPayRequest;
import com.srswitch.dao.RoutingDAO;
import com.srswitch.exception.BusinessException;
import com.srswitch.exception.SystemException;
import com.srswitch.model.Registration;

/**
 * @author nitin.malik
 * 
 * This class performs all the data validation related operations.
 * Responsible for validating :
 * 		1. AuthToken
 * 		2. Various Requests(Terminal Registration, Sales etc)
 *
 */
@Component
public class RequestValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestValidator.class);
	private static boolean isDebugEnabled = LOGGER.isDebugEnabled();
	
	private String GENERAL_VALIDATION = "generalValidation"; 
	private StatefulKnowledgeSession kSession = null;
	
	@Autowired
	RoutingDAO routingDAO; 
	
	
	/**
	 * Validates every incoming request based on the authentication token and terminal data 
	 * to make sure it comes from the registered terminal only or else will 
	 * discards the request and sends a message back to the client.
	 * 
	 * @param srPayRequest
	 */
	public void validateAuthToken(SrPayRequest srPayRequest)throws BusinessException{
		
		String authToken = srPayRequest.getAuthToken();
		if(authToken == null){
			LOGGER.info("Auth Token validation failed");
			throw new BusinessException("validate.authtoken.empty");
		}
		DeviceProfileType deviceProfile = srPayRequest.getDeviceProfile();

		String MSIDN = deviceProfile.getMSISDN();
		String IMEI = deviceProfile.getIMEI();
		String IMSI = deviceProfile.getIMSI();
 
		Registration registration = routingDAO.getRegistrationDetails(MSIDN, IMEI, IMSI);
		
		if (isDebugEnabled)
			LOGGER.debug("Validating Terminal Registration having :\nIMEI - "
					+ IMEI + "\nIMSI - " + IMSI + "\nMSIDN - " + MSIDN + " \nwith authToken : " + authToken);
		
		if(registration == null || !registration.getAuthToken().equals(authToken)) {
			throw new BusinessException("validate.authtoken.invalid");
		}
	}
	
	
	/**
	 * Generate Authentication Token using Secure Random API of Java
	 * 
	 * @return
	 * @throws SystemException
	 */
	public String generateAuthToken()throws SystemException{
		
		String authToken = null;
		try{
			SecureRandom secureRandom = new SecureRandom();
			byte[] seed = secureRandom.generateSeed(18);
			
			authToken = byteArrayToHexString(seed).toLowerCase();
		
		}catch(Exception exp){
			throw new SystemException(exp);
		}
		
		authToken = UUID.randomUUID().toString();
		if(isDebugEnabled) LOGGER.debug("Auth token generated : " + authToken);
		
		return authToken;
		
	}
	
	
	public static char NIBBLE_TO_CHAR[] = {'0', '1', '2', '3',
        '4', '5', '6', '7',
        '8', '9', 'A', 'B',
        'C', 'D', 'E', 'F'};
	
	
	/**
	 * Convert and returns the bytearray into hex string
	 * 
	 * @param data
	 * @return
	 */
	public String byteArrayToHexString(byte[] data){
		StringBuffer buffer = new StringBuffer();
		
		if(data!=null){
			for(int i = 0 ; i < data.length; i++){
				byte b = data[i];
				int I = ((char)b) & 0xFF;
				buffer.append(NIBBLE_TO_CHAR[I >>> 4]);
				buffer.append(NIBBLE_TO_CHAR[I & 0x0F]);

			}
		}
		return buffer.toString();
	}
	
	/**
	 * Initialize the Knowledge Session
	 * 
	 * @param srPayRequest
	 * @return
	 * @throws SystemException
	 */
	private StatefulKnowledgeSession initializeKnowledgeSession(SrPayRequest srPayRequest)throws SystemException{
	
		try{
			KnowledgeBase kBase = ValidationEngine.createKnowledgeBase();
			if(isDebugEnabled)  LOGGER.debug("Knowledge Base returned from validation engine : " + kBase);
			
			kSession = kBase.newStatefulKnowledgeSession();
			kSession.insert(srPayRequest);
			kSession.setGlobal("LOGGER", LOGGER);
			kSession.setGlobal("isDebugEnabled", isDebugEnabled);
		}catch(Exception exp){
			throw new SystemException("Exception occured while creating knowledge Session");
		}
		return kSession;
	}
	
	
	/**
	 * Performs request specific validations on the data received in request from Client POS.
	 * 
	 * @param srPayRequest
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public void performBusinessRuleValidations(SrPayRequest srPayRequest)throws BusinessException, SystemException{

		if(srPayRequest == null)  throw new BusinessException("generic.req.validation");
		else if(srPayRequest.getRegRequestData() == null) throw new BusinessException("generic.req.validation");
		
		//Perform Business Rule Validations
		try{	
			kSession = initializeKnowledgeSession(srPayRequest);
			
			if(isDebugEnabled) LOGGER.debug("Firing General validation rules having agenda-group : " + GENERAL_VALIDATION);
			kSession.getAgenda().getAgendaGroup(GENERAL_VALIDATION).setFocus();
			kSession.fireAllRules();

			if(isDebugEnabled) LOGGER.debug("Firing all validations rules having agenda-group : " + srPayRequest.getProcessingCode());
			kSession.getAgenda().getAgendaGroup(srPayRequest.getProcessingCode()).setFocus();
			kSession.fireAllRules();
			kSession.dispose();
			
			if(isDebugEnabled) LOGGER.debug("Validation rules fired Successfully");
			
		}catch(ConsequenceException consequenceException){
			
			Throwable throwable = consequenceException.getCause();
			if (throwable instanceof BusinessException) {
				throw new BusinessException(((BusinessException) throwable).getExceptionCode());
			} else if (throwable instanceof SystemException) {
				throw new SystemException(throwable);
			}
			
		}catch(Exception exp){
			
			if(isDebugEnabled) LOGGER.error("Exception occured while Performing General Validations : ", exp);
			throw new SystemException("Exception occured while performing general validations");
		}
	}	
}

