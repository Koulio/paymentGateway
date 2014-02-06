package com.srswitch.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.payment.model.ProdListResponseType;
import com.payment.model.RegResponseDataType;
import com.payment.model.ResponseDataType;
import com.payment.model.SrPayRequest;
import com.payment.model.SrPayResponse;
import com.srswitch.exception.BusinessException;
import com.srswitch.service.RoutingService;
import com.srswitch.validation.ProcessingCode;

/**
 * @author nitin.malik
 * 
 * Implementation class for routing switch.
 * Switch will mainly act as an interface between POS and Payment Gateway (Internal or External). 
 * 
 * Various functions performed by Switch : 
 * 1.	Wrap Request to JSON:
 * 2.	Validate Authentication Token
 * 3.	Perform General Business Validations
 * 4. 	Payment Gateway Adaptor Resolution
 * 5.	Logging of Incoming Request 
 * 
 * Switch: (table: routing, log )
	1) Message type NOT valid (MTI)
	2) Processing code NOT valid (Credit,Debit,Prepaid)
 * 
 */
@Controller
@RequestMapping(value = "/switch")
public class SrSwitch {

	private static final Logger LOGGER = LoggerFactory.getLogger(SrSwitch.class);
	
	@Autowired
	RoutingService routingService;
	
	@Autowired
	MessageSource messageSource;

	/**
	 * Handles the JSON Request (Containing Card Details, authToken etc) received from the client and 
	 * sends back the response in JSON format to the client.
	 * Lookup and route the request to appropriate handler (External or Internal Payment Gateway) based on Card Bin received in the request.
	 * 
	 * URL to be used by the client : http://<url>:<port>/PaymentSwitch/switch
	 * 
	 * @param srPayRequest
	 * @param request
	 * @return SrPayResponse in json format
	 * @throws BusinessException
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public SrPayResponse processJSONRequest(@RequestBody SrPayRequest srPayRequest, HttpServletRequest request){
		
		SrPayResponse srPayResponse = null;

		try{
			srPayResponse = routingService.processRequest(srPayRequest);
		}catch(BusinessException bussExp){
			srPayResponse = handleBusinessException(bussExp, srPayRequest);
		}catch(Exception sysExp){
			srPayResponse = handleSystemException(sysExp, srPayRequest);
		}

		return srPayResponse;
	}
	
	
	/**
	 * Handles the Business Exceptions i.e. Exceptions related to business rule validations
	 * 
	 * @param businessExp
	 * @return
	 */
	private SrPayResponse handleBusinessException(BusinessException businessExp, SrPayRequest srPayRequest){
		
		int result = 20;
		String exceptionMessage = null;
		
		LOGGER.error("Business Exception occured on Switch : " + businessExp);
		
		if(businessExp.getExceptionCode() != null){
			LOGGER.debug("Getting Exception Message for exception code : " + businessExp.getExceptionCode());
			exceptionMessage = getMessage(businessExp.getExceptionCode());	
		}else{
			exceptionMessage = getMessage("generic.req.validation");
		}
		
		LOGGER.error("Business Exception : " , exceptionMessage , businessExp);
		
		String processingCode = null;
		if(srPayRequest != null) 	processingCode = srPayRequest.getProcessingCode();
		
		return populateErrSrPayResponse(processingCode, result, exceptionMessage);
	}
	
	
	/**
	 * Handles the System Exceptions i.e. Exceptions from java code 
	 * 
	 * @param exception
	 * @param operType
	 * @return
	 */
	private SrPayResponse handleSystemException(Exception exception, SrPayRequest srPayRequest){
		
		int result = 20;
		
		LOGGER.error("Business Exception occured on Switch : " + exception);
		
		LOGGER.debug("Getting Exception Message for exception code : generic.req.validation");
		String exceptionMessage = getMessage("generic.req.validation");
		
		LOGGER.error("System Exception : " , exceptionMessage , exception);
		
		String processingCode = null;
		if(srPayRequest != null) processingCode = srPayRequest.getProcessingCode();
		
		return populateErrSrPayResponse(processingCode, result, exceptionMessage);
	}
	
	/**
	 * Method populates and returns the SrPayResponse for error
	 * based on the Processing Code receives in the request.  
	 * 
	 * @param processCode
	 * @param result
	 * @param description
	 * @return
	 */
	private SrPayResponse populateErrSrPayResponse(String processCode, int result, String description){
		
		SrPayResponse srPayResponse = new SrPayResponse();
		
		if(processCode == null){
			RegResponseDataType responseDataType = new RegResponseDataType();
			responseDataType.setResult(result);
			responseDataType.setDescription(description);
			srPayResponse.setRegResponseData(responseDataType);
			return srPayResponse;
			
		}else if(processCode.equalsIgnoreCase(ProcessingCode.TERMINAL_REGISTRATION)){
			RegResponseDataType responseDataType = new RegResponseDataType();
			responseDataType.setResult(result);
			responseDataType.setDescription(description);
			srPayResponse.setRegResponseData(responseDataType);
			return srPayResponse;
			
		}else if(processCode.equalsIgnoreCase(ProcessingCode.GET_PRODUCT_LIST)){
			ProdListResponseType prodListResponseType = new ProdListResponseType();
			prodListResponseType.setResult(result);
			prodListResponseType.setDescription(description);
			srPayResponse.setProdListResponseData(prodListResponseType);
			return srPayResponse;
			
		}
		else if(processCode.equalsIgnoreCase(ProcessingCode.SALES_TRANSACTION)){
			ResponseDataType salesResponseDataType = new ResponseDataType();
			salesResponseDataType.setResult(result);
			salesResponseDataType.setDescription(description);
			srPayResponse.setSalesResponseData(salesResponseDataType);
			return srPayResponse;
		}
		
		else if(processCode.equalsIgnoreCase(ProcessingCode.DEPOSIT_TRANSACTION)){
			ResponseDataType depositResponseDataType = new ResponseDataType();
			depositResponseDataType.setResult(result);
			depositResponseDataType.setDescription(description);
			srPayResponse.setDepositResponseData(depositResponseDataType);
			return srPayResponse;
		}
		
	/*	else if(processCode.equalsIgnoreCase(ProcessingCode.TRANSACTION_REFUND)){
			ResponseDataType salesResponseDataType = new ResponseDataType();
			salesResponseDataType.setResult(result);
			salesResponseDataType.setDescription(description);
			srPayResponse.setSalesResponseData(salesResponseDataType);
			return srPayResponse;
		}*/
		
		
		else{
			RegResponseDataType responseDataType = new RegResponseDataType();
			responseDataType.setResult(result);
			responseDataType.setDescription(description);
			srPayResponse.setRegResponseData(responseDataType);
			return srPayResponse;
		}
	}
	
	
	
	/**
	 * Returns the value corresponding to provided key from Message Source 
	 * having handler to properties files.
	 * 
	 * @param key
	 * @return message
	 */
	private String getMessage(String key){
		return messageSource.getMessage(key, null, Locale.US);
	}
	
	
	/**
	 * Handle the XML Request (Containing Card Details, authToken etc) received from the client and 
	 * sends back the response in XML format to the client.
	 * Lookup and route the request to appropriate handler based on Card Bin received in the request.
	 * 
	 * URL to be used by the client : http://<url>:<port>/PaymentSwitch/switch
	 * 
	 * @param srPayRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/xml", produces = "application/xml")
	@ResponseBody
	public SrPayResponse processXMLRequest(@RequestBody SrPayRequest srPayRequest, HttpServletRequest request)throws Exception{
		
		/*TODO
		 * # Validate Auth Token
		 * # General business rule validation
		 * # Call to service layer for resolving adaptor
		 */
		
		System.out.println("Processing XML Request");
		/*SrPayResponse srPayResponse = routingService.processRequest(srPayRequest);*/
		
		System.out.println(getMessage("generic.req.validation"));
		
		/*StatefulKnowledgeSession knowledgeSession = ValidationEngine.createKnowledgeSession();
		System.out.println("knowledgeSession : " + knowledgeSession);
		
		RoutingDAO routingDAO = new RoutingDAO();
		knowledgeSession.getAgenda().getAgendaGroup("Deposit").setFocus();
		
		System.out.println("knowledgeSession : " + knowledgeSession.getId());
		knowledgeSession.setGlobal("routingDAO", routingDAO);
		knowledgeSession.insert(srPayRequest);
		System.out.println("Facts Inserted || Firing Rules");
		System.out.println(knowledgeSession.getGlobal("routingDAO"));
		knowledgeSession.fireAllRules();
		*/
		SrPayResponse srPayResponse = new SrPayResponse();
		srPayResponse.setAuthToken("asdasddassadas");
		srPayResponse.setVersion(1.0f);
		return srPayResponse;
	}

	
	/**
	 * Handle the ISO 8583 Request (Containing Card Details, authToken etc) received from the client and
	 * sends back the response in ISO format to the client.
	 * Lookup and route the request to appropriate handler based on Card Bin received in the request.
	 * 
	 * URL to be used by the client : http://<url>:<port>/PaymentSwitch/switch
	 * 
	 * @param request
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/iso", produces = "application/iso")
	public void processISORequest(HttpServletRequest request, HttpServletResponse response)throws Exception{
	
		System.out.println("SrSwitch.processISORequest()");
	
		/*TODO
		 * # Call to Request Factory and populate the SrPayRequest(JSON) using JPOS, UICC Packager etc
		 * # General business rule validation
		 * # Call to service layer for resolving adaptor
		 * */
		
	}	
}
