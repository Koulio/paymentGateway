package com.srswitch.validation;

/**
 * @author nitin.malik
 * 
 * Message Type Code defines the high level function of the message. Includes:
 * 1. ISO-8583 Version
 * 		0 - ISO 8583:1987
 * 		1 - ISO 8583:1993 
 *  	2 - ISO 8583:2003
 * 2. MTI - Message Type Identifier : Consists of following:
 * 		a. Message Class
			x1xx	Authorization Message	
			x2xx	Financial Messages	
			x3xx	File Actions Message	
			x4xx	Reversal Message	
			x5xx	Reconciliation Message	
			x6xx	Administrative Message	
			x7xx	Fee Collection Messages	
			x8xx	Network Management Messages
			x9xx	Reserved by ISO	        	
 * 		b. Message Function
		 	xx0x	Request
			xx1x	Request Response
			xx2x	Advice
			xx3x	Advice Response
			xx4x	Notification
			xx8x	Response acknowledgment
			xx9x	Negative acknowledgment
 * 		c. Transaction Originator
			xxx0	Acquirer
			xxx1	Acquirer Repeat
			xxx2	Issuer
			xxx3	Issuer Repeat
			xxx4	Other
			xxx5	Other Repeat
 *  
 *  MTI and Process Codes are interrelated to each other.
 *
 */
public interface MessageTypeCode {


	public static final Integer	ADMINISTRATIVE_REQUEST							= 2604;
	public static final Integer ADMINISTRATIVE_REQUEST_RESPONSE					= 2614;
	
	public static final	Integer  AUTHORIZATION_REQUEST							= 2100;
	public static final	Integer  AUTHORIZATION_REQUEST_RESPONSE					= 2110;
	
	public static final	Integer  FINANCIAL_PRESENTMENT_REQUEST					= 2200;
	public static final	Integer  FINANCIAL_PRESENTMENT_REQUEST_RESPONSE			= 2210;
	
	
	
/*	public static final	String  REVERSAL_REQUEST						= "2400";
	public static final	String  REVERSAL_REQUEST2						= "2404";
	public static final	String  REVERSAL_RESPONSE						= "2410";	
	public static final	String  REFUND_REQUEST							= "2400";
	public static final	String  REFUND_RESPONSE							= "2410";
	
	
	 For User Wallet Services 
	public static final String  USER_ADMINISTRATIVE_REQUEST				= "2006";
	public static final String  USER_ADMINISTRATIVE_REQUEST_RESPONSE	= "2016";
	
	public static final String  USER_AUTHORIZATION_REQUEST				= "2007";
	public static final String  USER_AUTHORIZATION_REQUEST_RESPONSE		= "2017";
	
	public static final String  USER_FINANCIAL_PRESENTMENT_REQUEST				= "2008";
	public static final String  USER_FINANCIAL_PRESENTMENT_REQUEST_RESPONSE		= "2018";
	
	public static final String  RFU1_REQ		= "2906";
	public static final String  RFU1_RES		= "2916";
	
	public static final String  RFU2_REQ		= "2907";
	public static final String  RFU2_RES		= "2917";
	
	public static final String  RFU3_REQ		= "2908";
	public static final String  RFU3_RES		= "2918";*/
	
}
