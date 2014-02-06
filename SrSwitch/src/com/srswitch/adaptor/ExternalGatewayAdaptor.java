package com.srswitch.adaptor;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.payment.model.SrPayRequest;
import com.payment.model.SrPayResponse;
import com.srswitch.model.Serviceendpoint;

/**
 * @author nitin.malik
 * 
 * This implementation of gateway adaptor will be invoked when request received
 * is for external gateway.
 * This gateway adaptor will call the External Payment Gateway ex: VISA, AMEX, MASTERCARD
 *
 */
@Service
@Qualifier(value = "ExternalGatewayAdaptor")
public class ExternalGatewayAdaptor implements GatewayAdaptor, Serializable {

	private static final long serialVersionUID = 433762907056443076L;
	private String GATEWAY_ADAPTOR_NAME = "ExternalGatewayAdaptor";

	@Override
	public SrPayResponse processTransaction(SrPayRequest srPayRequest, Serviceendpoint serviceendpoint)throws Exception {
		
		//TODO : Call to External Gateway (VISA, MasterCard)
		
		SrPayResponse srPayResponse = null;		
		System.out.println("Inside External Gateway");
		
		return srPayResponse;
	}

	@Override
	public String getName() {
		return this.GATEWAY_ADAPTOR_NAME;
	}
	
	

}
