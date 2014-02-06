package com.srswitch.adaptor;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.payment.model.SrPayRequest;
import com.payment.model.SrPayResponse;
import com.srswitch.exception.SystemException;
import com.srswitch.model.Serviceendpoint;

/**
 * @author nitin.malik
 *	
 *	This implementation of Gateway Adaptor will be called 
 *
 */
@Service
@Qualifier("SidreyaGatewayAdaptor")
public class DefaultGatewayAdaptor implements GatewayAdaptor, Serializable {

	private static final long serialVersionUID = 1252474989527248311L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGatewayAdaptor.class);
	private static boolean isDebugEnabled = LOGGER.isDebugEnabled();
	
	private String GATEWAY_ADAPTOR_NAME = "SidreyaGatewayAdaptor";

	@Override
	public SrPayResponse processTransaction(SrPayRequest srPayRequest, Serviceendpoint serviceendpoint)throws Exception {
	
		SrPayResponse srPayResponse = null;
		URI serviceURI = null;
		//TODO : Currently only WebService implementation to call SidreyaPaymentGateway is made using RestTemplate
		// Implementation for Calling RestFul Payment Gateway service using Spring Remoting (HttpInvoker) is pending.
		
		if (isDebugEnabled)
			LOGGER.debug("Calling Payment Gateway Service : \n  Service Name : "
					+ serviceendpoint.getServiceName()
					+ "\n  ServiceDescription : "
					+ serviceendpoint.getServiceDescription()
					+ "\n  ServiceURL : " + serviceendpoint.getServiceUrl());
		
		RestTemplate restTemplate = new RestTemplate();
		try{
			serviceURI = new URI(serviceendpoint.getServiceUrl());
			restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
				
			srPayResponse = restTemplate.postForObject(serviceURI, srPayRequest, SrPayResponse.class);
			/*restTemplate.postForObject("http://122.160.41.253:85/srpaymentgateway/srpay/sendReceipt/", srPayRequest, SrPayResponse.class);*/
		}catch(URISyntaxException exp){
			
			LOGGER.error("Error while calling a Payment Gateway service : " + serviceendpoint.getServiceName());
			throw new SystemException(exp);
		}
		return srPayResponse; 
	}
	
	
	public static void main(String[] args){
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/SrSwitch/srSwitch/switch/";
		SrPayRequest payRequest = new SrPayRequest();
		payRequest.setAuthToken("asdasd");
		
		try {
			SrPayResponse response = restTemplate.postForObject(new URI(url), payRequest, SrPayResponse.class);
			System.out.println(response.getAuthToken());
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}


	@Override
	public String getName() {
		return this.GATEWAY_ADAPTOR_NAME;
	}

}
