package com.srswitch.service;

import org.springframework.stereotype.Component;

import com.payment.model.SrPayRequest;
import com.payment.model.SrPayResponse;
import com.srswitch.exception.BusinessException;
import com.srswitch.exception.SystemException;

@Component
public interface RoutingService {

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
	public SrPayResponse processRequest(SrPayRequest srPayRequest)throws BusinessException, SystemException;
}
