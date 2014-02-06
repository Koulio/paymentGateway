package com.srswitch.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.codehaus.jackson.map.ObjectMapper;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.ConsequenceException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.payment.model.BasketDetailsType;
import com.payment.model.CardDetailsType;
import com.payment.model.CustIPType;
import com.payment.model.DeviceProfileType;
import com.payment.model.OtherDataType;
import com.payment.model.ReceiptRequestDataType;
import com.payment.model.RegRequestDataType;
import com.payment.model.SalesRequestDataType;
import com.payment.model.SalesTransactionDataType;
import com.payment.model.SrPayRequest;
import com.payment.model.SrPayResponse;
import com.payment.model.TerminalDataType;
import com.payment.model.TransactionDataType;
import com.srswitch.exception.BusinessException;
import com.srswitch.exception.SystemException;
import com.srswitch.validation.MessageTypeCode;
import com.srswitch.validation.ProcessingCode;

public class TestSwitch {
	
	//private static final String OPERATION_PERFORM = "REGISTRATION"; 
	
	//private static final String OPERATION_PERFORM = "GET_PRODUCT_LIST"; 
	
	//private static final String OPERATION_PERFORM = "SALES"; 
	
	private static final String OPERATION_PERFORM = "SEND_RECEIPT"; 
	
	private static final String MSISDN = "9711355293";
	private static final String IMSI = "3565550559451008/01";
	private static final String IMEI = "3565550559451008/02";
	private static final String UNIQUE_CODE = "MyTerminal1";
	
	private static final String AUTH_TOKEN = "ed8b4e50-4568-4ff9-a421-ea1d5d05d391";
	
	private static final String SERVICE_URL = "http://localhost:85/SrSwitch/switch/";
	
	public static void main(String[] args){
	
		SrPayRequest srPayRequest = null;
		if(OPERATION_PERFORM.equalsIgnoreCase("REGISTRATION")){
			srPayRequest = prepareRegistrationRequest();
		}else if(OPERATION_PERFORM.equalsIgnoreCase("GET_PRODUCT_LIST")){
			srPayRequest = prepareGetProductListRequest();
		}else if(OPERATION_PERFORM.equalsIgnoreCase("SALES")){
			srPayRequest = prepareSalesRequest();
		}else if(OPERATION_PERFORM.equalsIgnoreCase("SEND_RECEIPT")){
			srPayRequest = prepareSendReceiptRequest();
		}
		
		marshalRequest(srPayRequest); //Method to Marshall SrPayRequest (Object to JSON)
		//makeRequest(srPayRequest);
		//testDroolsValidations(srPayRequest); // Method to test drools validations
	}
	
	private static SrPayRequest prepareSendReceiptRequest(){

		TerminalDataType terminalDataType = new TerminalDataType();
		terminalDataType.setUniqueCode(UNIQUE_CODE);
		terminalDataType.setMerchantCode("8162723");
		terminalDataType.setMerchantName("Nitin & Sons");
		terminalDataType.setStoreAdd("D-94, Sec-63");
		terminalDataType.setCity("Noida");
		terminalDataType.setState("UP");
		terminalDataType.setZip(110092);
		
		
		
		RegRequestDataType requestDataType = new RegRequestDataType();
		requestDataType.setTerminalData(terminalDataType);
		
		TransactionDataType transactionDataType = new TransactionDataType();
		transactionDataType.setRefNo("0054785");
		transactionDataType.setTransactionAmount(100F);
		transactionDataType.setDateTime(System.currentTimeMillis());
		transactionDataType.setCurrency("INR");
		transactionDataType.setTerminalCode("0000");

		SalesTransactionDataType salesTransactionDataType = new SalesTransactionDataType();
		salesTransactionDataType.setTransactionData(transactionDataType);
		
		CardDetailsType cardDetailsType =  new CardDetailsType();
		cardDetailsType.setCardNumber("4567891235465214");
		
		SalesRequestDataType salesRequestDataType = new SalesRequestDataType();
		salesRequestDataType.setSalesTransactionData(salesTransactionDataType);
		salesRequestDataType.setCardDetails(cardDetailsType);

		OtherDataType otherDataType = new OtherDataType();
		otherDataType.setPrepaidBalance(795F);
		otherDataType.setReceiptMessage("Approved");
		otherDataType.setRewardBalance(45F);
		otherDataType.setRewardsEarned(10F);
		otherDataType.setRewardsRedeemed(0F);
		
		ReceiptRequestDataType receiptRequestDataType = new ReceiptRequestDataType();
		receiptRequestDataType.setApprovalCode("12340");
		receiptRequestDataType.setOtherDataType(otherDataType);
		/*receiptRequestDataType.setEmailId("sunil.yadav@ireslab.com");
		receiptRequestDataType.setSendEmail(true);*/
		receiptRequestDataType.setSendSMS(true);
		receiptRequestDataType.setMobileNum("919416546882");
		
		
		SrPayRequest srPayRequest = new SrPayRequest();
		srPayRequest.setAuthToken(AUTH_TOKEN);
		srPayRequest.setReceiptRequestDataType(receiptRequestDataType);
		srPayRequest.setMTI(MessageTypeCode.AUTHORIZATION_REQUEST );
		srPayRequest.setProcessingCode(ProcessingCode.SEND_RECEIPT);
		srPayRequest.setDeviceProfile(getDeviceProfile());
		srPayRequest.setRegRequestData(requestDataType);
		srPayRequest.setSalesRequestData(salesRequestDataType);
		
		return srPayRequest;
	}
	
	
	private static void makeRequest(SrPayRequest srPayRequest){
	
		RestTemplate restTemplate = new RestTemplate();
		try{
			URI serviceURI = new URI(SERVICE_URL);
			restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
			
			HttpHeaders headers = new HttpHeaders();  
	        headers.setContentType( MediaType.APPLICATION_JSON );  
	  
	        HttpEntity request= new HttpEntity( srPayRequest, headers );
				
			SrPayResponse srPayResponse = restTemplate.postForObject(serviceURI, request, SrPayResponse.class);
			System.out.println("Response from srPayResponse : ");
			marshalResponse(srPayResponse);
			
		}catch(URISyntaxException exp){
			exp.printStackTrace();
		}
	}
	
	
	
	private static SrPayRequest prepareRegistrationRequest(){
		
		TerminalDataType terminalDataType = new TerminalDataType();
		terminalDataType.setUniqueCode(UNIQUE_CODE);
		
		RegRequestDataType requestDataType = new RegRequestDataType();
		requestDataType.setTerminalData(terminalDataType);
		
		SrPayRequest srPayRequest = new SrPayRequest();
		srPayRequest.setMTI(MessageTypeCode.ADMINISTRATIVE_REQUEST);
		srPayRequest.setProcessingCode(ProcessingCode.TERMINAL_REGISTRATION);
		srPayRequest.setDeviceProfile(getDeviceProfile());
		srPayRequest.setRegRequestData(requestDataType);
		
		return srPayRequest;
	}
	
	private static DeviceProfileType getDeviceProfile(){
		DeviceProfileType deviceProfileType = new DeviceProfileType();
		deviceProfileType.setMSISDN(MSISDN);
		deviceProfileType.setIMSI(IMSI);
		deviceProfileType.setIMEI(IMEI);
		
		return deviceProfileType;
	}
	
	private static SrPayRequest prepareGetProductListRequest(){

		TerminalDataType terminalDataType = new TerminalDataType();
		terminalDataType.setUniqueCode(UNIQUE_CODE);
		
		RegRequestDataType requestDataType = new RegRequestDataType();
		requestDataType.setTerminalData(terminalDataType);
		
		SrPayRequest srPayRequest = new SrPayRequest();
		srPayRequest.setAuthToken(AUTH_TOKEN);
		srPayRequest.setMTI(MessageTypeCode.ADMINISTRATIVE_REQUEST);
		srPayRequest.setProcessingCode(ProcessingCode.GET_PRODUCT_LIST);
		srPayRequest.setDeviceProfile(getDeviceProfile());
		srPayRequest.setRegRequestData(requestDataType);
		
		return srPayRequest;
	}
	
	
	private static SrPayRequest prepareSalesRequest(){
		
		
		TerminalDataType terminalDataType = new TerminalDataType();
		terminalDataType.setUniqueCode(UNIQUE_CODE);
		
		RegRequestDataType regRequestDataType = new RegRequestDataType();
		regRequestDataType.setTerminalData(terminalDataType);
		
		CardDetailsType cardDetailsType = new CardDetailsType();
		cardDetailsType.setCardNumber("900000001");
		cardDetailsType.setTrack1("%B1234567890123445^PADILLA/L.                ^99011X100000*000000000XXX000000?*");
		cardDetailsType.setTrack2(";1234567890123445=99011X10XXXXXXX00000?*");
		cardDetailsType.setExpiryDate("12/12/2");
		cardDetailsType.setSecurityCode("SC01");
		cardDetailsType.setNameOnCard("N. Malik");
		cardDetailsType.setAdd2("Delhi");
		cardDetailsType.setAdd1("Nirman Vihar");
		cardDetailsType.setCity("Delhi");
		cardDetailsType.setState("Delhi");
		cardDetailsType.setCountry("India");
		cardDetailsType.setZip("110092");

		TransactionDataType dataType = new TransactionDataType();
		dataType.setCurrency("INR");
		dataType.setDateTime(System.currentTimeMillis());
		dataType.setMerchantCode("MER CODE");
		dataType.setTerminalCode("TERM CODE");
		dataType.setTransactionAmount(125.5F);
		dataType.setRefNo("RefNo1234566");
		
		CustIPType custIPType  = new CustIPType();
		custIPType.setIPAdd("192.168.1.6");
		custIPType.setMACAdd("D4-BE-D9-40-21-A5");
		
		BasketDetailsType basketDetailsType = new BasketDetailsType();
		basketDetailsType.setProductsku("sku");
		basketDetailsType.setCost(25F);
		basketDetailsType.setQuantity(15L);
		
		
		
		
		SalesTransactionDataType salesTransactionDataType = new SalesTransactionDataType();
		salesTransactionDataType.setTax1(2);
		salesTransactionDataType.setTax2(4);
		salesTransactionDataType.setTax3(5);
		salesTransactionDataType.setTransactionData(dataType);
		
		SalesRequestDataType requestDataType = new SalesRequestDataType();
		requestDataType.setCardDetails(cardDetailsType);
		requestDataType.setSalesTransactionData(salesTransactionDataType);
		requestDataType.setCashierCode("CC1234");
		requestDataType.setCustIP(custIPType);
		
		SrPayRequest payRequest = new SrPayRequest();
		payRequest.setMTI(MessageTypeCode.FINANCIAL_PRESENTMENT_REQUEST);
		payRequest.setProcessingCode(ProcessingCode.SALES_TRANSACTION);
		payRequest.setAuthToken(AUTH_TOKEN);
		payRequest.setRegRequestData(regRequestDataType);
		payRequest.setDeviceProfile(getDeviceProfile());
		payRequest.setSalesRequestData(requestDataType);
		
		return payRequest;
	}
	
	private static void testDroolsValidations(SrPayRequest srPayRequest){
		
		try{
			KnowledgeBase knowledgeBase = createKnowledgeBase();
			StatefulKnowledgeSession kSession = knowledgeBase.newStatefulKnowledgeSession();
			kSession.getAgenda().getAgendaGroup("generalValidation").setFocus();
			kSession.insert(srPayRequest);
			System.out.println("Firing all rules ");
			kSession.fireAllRules();
			System.out.println("Rules fired ");
			
		}catch(ConsequenceException exception){
			
			System.out.println("Consequence Exception : " + exception);
			Throwable throwable = exception.getCause();
			if(throwable instanceof BusinessException){
				System.err.print(((BusinessException) throwable).getExceptionCode());
				throwable.printStackTrace();
			}else if(throwable instanceof SystemException){
				System.err.print(((SystemException) throwable).getLocalizedMessage());
				throwable.printStackTrace();
			}
		}catch(Exception exception){
			System.out.println("Exception Occured");
			exception.printStackTrace();
		}
	}
	
	
	private static void marshalResponse(SrPayResponse srPayResponse){
		
		try{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(System.out, srPayResponse);
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	
	private static void marshalRequest(SrPayRequest srPayRequest){
		
		try{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(System.out, srPayRequest);
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	
	private static KnowledgeBase createKnowledgeBase(){
		
		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		knowledgeBuilder.add(ResourceFactory.newClassPathResource("com/srswitch/validation/rules/test/SW_GeneralValidationRules.drl"), ResourceType.DRL);
	
		if(!knowledgeBuilder.getErrors().isEmpty()){
			System.out.println("knowledgeBuilder.getErrors()  : " + knowledgeBuilder.getErrors());
			Iterator<KnowledgeBuilderError> iterator = knowledgeBuilder.getErrors().iterator();   
			while(iterator.hasNext()){
				KnowledgeBuilderError error = iterator.next();
				System.out.println("Message : " + error.getMessage());
				System.out.println("Resource : " + error.getResource().toString());
				System.out.println("Severity : " + error.getSeverity());
				System.out.println(error);
			}
			System.out.println("Errors occured in knowledge : ");
		}
		
		System.out.println("Creating Knowledge Base");
		KnowledgeBase knowledgeBase = knowledgeBuilder.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
		
		return knowledgeBase;
	}

}










/*
payRequest.setMTI(2200);
payRequest.setProcessingCode("507878");
payRequest.setAuthToken("1a134aab-4f6d-4252-80e7-8564d57b49a3");

TerminalDataType terminalDataType = new TerminalDataType();
terminalDataType.setMerchantCode("MER CODE");
terminalDataType.setUniqueCode("UNIQUECODE2");

CardDetailsType cardDetailsType = new CardDetailsType();
cardDetailsType.setCardNumber("900000001");
cardDetailsType.setTrack1("%B1234567890123445^PADILLA/L.                ^99011X100000*000000000XXX000000?*");
cardDetailsType.setTrack2(";1234567890123445=99011X10XXXXXXX00000?*");
cardDetailsType.setExpiryDate("01/16");
cardDetailsType.setSecurityCode("SC01");
cardDetailsType.setNameOnCard("N. Malik");
cardDetailsType.setAdd2("Delhi");
cardDetailsType.setAdd1("Nirman Vihar");
cardDetailsType.setCity("Delhi");
cardDetailsType.setState("Delhi");
cardDetailsType.setCountry("India");
cardDetailsType.setZip("110092");


SalesRequestDataType requestDataType = new SalesRequestDataType();
requestDataType.setCardDetails(cardDetailsType);
payRequest.setSalesRequestData(requestDataType);

DeviceProfileType deviceProfileType = new DeviceProfileType();
deviceProfileType.setMSISDN("0177110926");
deviceProfileType.setIMSI("351746051160474");
deviceProfileType.setIMEI("470010110115827");
payRequest.setDeviceProfile(deviceProfileType);

TransactionDataType dataType = new TransactionDataType();
dataType.setCurrency("INR");
dataType.setDateTime(System.currentTimeMillis());
dataType.setMerchantCode("MER CODE");
dataType.setTerminalCode("TERM CODE");
dataType.setTransactionAmount(125.5F);
dataType.setRefNo("RefNo1234566");

CustIPType custIPType  = new CustIPType();
custIPType.setIPAdd("192.168.1.6");
custIPType.setMACAdd("D4-BE-D9-40-21-A5");

SalesTransactionDataType salesTransactionDataType = new SalesTransactionDataType();
salesTransactionDataType.setTax1(2);
salesTransactionDataType.setTax2(4);
salesTransactionDataType.setTax3(5);
salesTransactionDataType.setTransactionData(dataType);
requestDataType.setSalesTransactionData(salesTransactionDataType);
requestDataType.setCashierCode("CC1234");
requestDataType.setCustIP(custIPType);

RegRequestDataType regRequestDataType = new RegRequestDataType();
regRequestDataType.setTerminalData(terminalDataType);

payRequest.setRegRequestData(regRequestDataType);

payRequest.setSalesRequestData(requestDataType);*/