package com.srswitch.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.payment.model.CardDetailsType;
import com.payment.model.SrPayRequest;
import com.payment.model.SrPayResponse;
import com.srswitch.adaptor.GatewayAdaptor;
import com.srswitch.adaptor.GatewayAdaptorFactory;
import com.srswitch.dao.RoutingDAO;
import com.srswitch.exception.BusinessException;
import com.srswitch.exception.SystemException;
import com.srswitch.model.Cardrouting;
import com.srswitch.model.Serviceendpoint;
import com.srswitch.service.RoutingService;
import com.srswitch.validation.ProcessingCode;
import com.srswitch.validation.RequestValidator;

/**
 * @author nitin.malik
 * 
 * Implementation class of Service Layer which handles all the business logic.
 *
 */
@Service
public class RoutingServiceImpl implements RoutingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoutingServiceImpl.class);
	private boolean isDebugEnabled = LOGGER.isDebugEnabled();
	
	@Autowired
	private RoutingDAO routingDAO;

	@Autowired
	private GatewayAdaptorFactory adaptorFactory;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@Autowired
	MessageSource messageSource;
	
	private GatewayAdaptor adaptor;
	private static HashMap<String,Cardrouting> CACHED_BINRANGES = null;
	private static HashMap<String,Serviceendpoint> CACHED_SERVICE_ENDPOINTS = null;
	
	/**
	 * Performs the following functions:
	 * 1. Validate Authentication Token/ Generate Authentication Token (in case of Registration Request)
	 * 2. Resolve internal/external adaptor
	 * 	  a. Every Request except Sales will be transfered to Internal i.e. DefaultGatewayAdaptor
	 * 	  b. In case of Sales Request CardBin Range is used to resolve Internal/External Gateway
	 * 3. Returns the response received from Gateway Adaptor in SrPayResponse to Controller (SrSwitch). 
	 * 
	 * @param srPayRequest
	 * @return SrPayResponse
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@Override
	public SrPayResponse processRequest(SrPayRequest srPayRequest)throws BusinessException, SystemException {
				
		SrPayResponse srPayResponse = null;
		requestValidator.performBusinessRuleValidations(srPayRequest);					//1. Perform Data Validations
		
		try{
			String processingCode = srPayRequest.getProcessingCode();
			LOGGER.debug("Switch received request for '" + messageSource.getMessage(processingCode, null, Locale.US) + "'");
						
			if(!processingCode.equalsIgnoreCase(ProcessingCode.TERMINAL_REGISTRATION)){
				requestValidator.validateAuthToken(srPayRequest);						//2(b). Validating Authentication Token
			}
			else{
				generateAuthToken(srPayRequest);
			}
					
			String moduleName = null;
			Serviceendpoint serviceendpoint = null;
			
			if(processingCode.equalsIgnoreCase(ProcessingCode.SALES_TRANSACTION)){		//3. Resolving Adaptor if request is Sales Request based on Card Bin Range 
				Cardrouting cardRoute = deriveBinRange(srPayRequest.getSalesRequestData().getCardDetails());
				moduleName = cardRoute.getProcessModuleName();
				serviceendpoint = cardRoute.getServiceEndpointId();
			}
			else if(processingCode.equalsIgnoreCase(ProcessingCode.DEPOSIT_TRANSACTION)){ 
				Cardrouting cardRoute = deriveBinRange(srPayRequest.getDepositRequestData().getCardDetails());
				moduleName = cardRoute.getProcessModuleName();
				serviceendpoint = getServiceEndpointDetails(processingCode);
			}
			else if(processingCode.equalsIgnoreCase(ProcessingCode.TRANSACTION_REFUND)){ 
				Cardrouting cardRoute = deriveBinRange(srPayRequest.getSalesRequestData().getCardDetails());
				moduleName = cardRoute.getProcessModuleName();
				serviceendpoint = getServiceEndpointDetails(processingCode);
			}else{
				serviceendpoint = getServiceEndpointDetails(processingCode);
			}
				
			adaptor = adaptorFactory.getAdapter(moduleName);
			if(isDebugEnabled) LOGGER.debug("Adaptor Resolved for module Name : " + adaptor.getName());
			
			srPayResponse = adaptor.processTransaction(srPayRequest,serviceendpoint);	//4. Calling Appropriate Gateway Adaptor with SrPayRequest & ServiceEndpoint details
		/*	
			
			if (processingCode.equalsIgnoreCase(ProcessingCode.TERMINAL_REGISTRATION)
					&& srPayResponse.getRegResponseData().getResult() == 0) {
				srPayRequest.setAuthToken(requestValidator.generateAuthToken()); // Generating Authentication Token (Registration Request)
			}*/
			
		} catch(BusinessException businessExp){
			throw new BusinessException(businessExp);
		} catch (Exception exp) {
			LOGGER.error("Exception occured : ", exp);
			throw new SystemException(exp);
		}
		return srPayResponse;
	}
	
	private void generateAuthToken(SrPayRequest srPayRequest)
	{
		srPayRequest.setAuthToken(UUID.randomUUID().toString());
	}
	/**
	 * Return Service Endpoint details based on operation Code
	 * 
	 * @param operCode
	 * @return
	 */
	private Serviceendpoint getServiceEndpointDetails(String processingCode)throws BusinessException, SystemException{
		
		Serviceendpoint serviceendpoint = null;
		
		if(CACHED_SERVICE_ENDPOINTS == null){
			CACHED_SERVICE_ENDPOINTS = new HashMap<String, Serviceendpoint>();
			
			List<Serviceendpoint> serviceendpoints = routingDAO.getConfiguredServiceEndpoints();
			for(Serviceendpoint servEndpoint : serviceendpoints){
				CACHED_SERVICE_ENDPOINTS.put(servEndpoint.getProcessingCode(), servEndpoint);
			}
		}
		
		if(isDebugEnabled) LOGGER.debug("Getting ServiceEndpoint details for operation Code : " + processingCode);
		
		Set<String> pubStrings = CACHED_SERVICE_ENDPOINTS.keySet();
		Iterator<String> itr = pubStrings.iterator();
		while(itr.hasNext()){
			String key = itr.next();
			System.out.println("Processing Code to be compared from Map : " + key);
			if(key.equalsIgnoreCase(processingCode)){
				serviceendpoint = CACHED_SERVICE_ENDPOINTS.get(processingCode);
				System.out.println("Service Endpoint retrieved : " + serviceendpoint);
				System.out.println("ServiceURL : " + serviceendpoint.getServiceUrl());
				break;
			}
		}
		
		if(serviceendpoint == null){
			LOGGER.error("No service endpoint details corresponding to processing code : " + processingCode);
			throw new BusinessException("generic.req.noendpointdetails");
		}
		
		return serviceendpoint;
	}
	
	
	/**
	 * Derive binrange for the card number
	 * 
	 * @param cardDetails
	 * @return
	 */
	private Cardrouting deriveBinRange(CardDetailsType cardDetails)throws BusinessException, SystemException {

		String partialCardNumber = cardDetails.getCardNumber().substring(0, 4);
		if(isDebugEnabled) LOGGER.debug("Deriving Bin Range for Card Number : " + partialCardNumber);
		
		Cardrouting cardRoute = null;
		if (CACHED_BINRANGES == null) {
			CACHED_BINRANGES = new HashMap<String, Cardrouting>();

			List<Cardrouting> cardRoutingList = routingDAO.getConfiguredBinRanges();

			for (int i = 0; i < cardRoutingList.size(); i++) {
				CACHED_BINRANGES.put(cardRoutingList.get(i).getBinStart() + "_"
						+ cardRoutingList.get(i).getBinEnd(),
						cardRoutingList.get(i));
			}
		}

		Set<String> en = CACHED_BINRANGES.keySet();
		Iterator<String> itr = en.iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			if (isInRange(partialCardNumber, CACHED_BINRANGES.get(key))) {
				cardRoute = CACHED_BINRANGES.get(key);
				break;
			}
		}
		return cardRoute;
	}
		
	
	/**
	 * Validating the bin range to derive the cardRouting record
	 * 
	 * @param partialCardNumber
	 * @param cardRoute
	 * @return
	 */
	private boolean isInRange(String partialCardNumber, Cardrouting cardRoute) {

		boolean isInRange = false;

		long start = Long.parseLong(cardRoute.getBinStart());
		long end = Long.parseLong(cardRoute.getBinEnd());

		if (partialCardNumber != null && partialCardNumber.length() >= cardRoute.getBinStart().length()) {
			long val = Long.parseLong(partialCardNumber.substring(0, cardRoute.getBinStart().length()));
			if (val >= start && val <= end) {
				isInRange = true;
			}
		}
		return isInRange;
	}
	
}
