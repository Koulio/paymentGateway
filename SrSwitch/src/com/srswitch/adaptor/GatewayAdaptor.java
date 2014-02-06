package com.srswitch.adaptor;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.payment.model.SrPayRequest;
import com.payment.model.SrPayResponse;
import com.srswitch.model.Serviceendpoint;

@Component
public interface GatewayAdaptor extends Serializable{

	/**
	 * This method will be implemented by Default Gateway Adaptor and External Gateway Adaptor.
	 * Contains implementation for making a call to an external or internal service of Payment 
	 * Gateway through ServiceEndpoint details.
	 * 
	 * @param srPayRequest
	 * @param serviceendpoint
	 * @return
	 * @throws Exception
	 */
	public SrPayResponse processTransaction(SrPayRequest srPayRequest, Serviceendpoint serviceendpoint)throws Exception;
	
	
	/**
	 * Return the name of the adaptor invoked.
	 * 
	 * @return
	 */
	public String getName();
}
