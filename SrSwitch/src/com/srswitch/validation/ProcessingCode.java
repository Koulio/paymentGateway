package com.srswitch.validation;

/**
 * @author nitin.malik
 * 
 * Processing Code is code used to describe the effect of
 * a transaction on customer account and accounts affected.
 * 
 * Processing Code consists of 3 parts:
 * 1. Transaction Type Code : First Bit of this code represents 
 * 	  the type of transaction and relation with MTI 
 * 	  For ex : 00-1Z - Debits
 *    		   20-2Z - Credits
 *    		   30-3Z - Inquiry/Verification Services
 *    		   40-4Z - Transfer Services
 *    		   50-5Z - Payment Services
 *    		   60-6Z - Electronic Purse Services
 *    		   70-7Z - Administrative Services	 (7S-7Z - RFU)
 *    		   80-8Z - Reserved 	(8S-8Z - Reserved for Private use)
 * 2. Account Type Code 1 (from)
 * 3. Account Type Code 2 (to)
 * 	  For ex : 01-09 - Default
 * 			   10-19 - Savings Account
 * 						(10 	- default)
 * 						(18-19	- reserved for private use)
 * 			   20-29 - Cheque Account
 * 			   30-39 - Credit Facility
 * 			   40-49 - Universal Account
 * 			   50-59 - Investment Account
 * 			   60-69 - Electronic Purse card account
 * 			   70-79 - Reserved (78-79 - Reserved for Private use)
 * 
 */
public class ProcessingCode {

	/* Administrative Services */
	//1. Administrative MTI : 2604, 2614 
	public static final String TERMINAL_REGISTRATION 			= "7S7878";
	public static final String GET_PRODUCT_LIST 				= "7T7878";
		
	/* Payment Services MTI : 2200, 2210
	 * 50	 - Customer Generated/Initiated Payment
	 * 51	 - Account Verification
	 * 5S-5Z - Reserved for private use
	 */
	public static final String SALES_TRANSACTION				= "507878";
	public static final String DEPOSIT_TRANSACTION				= "5S7878";
	

	/* Inquiry/Verification Services  
	 * 31 	 - Balance Enquiry
	 * 34 	 - Statement Enquiry
	 * 3S-3Z - Reserved for private use
	 */
	//1. Administrative MTI : 2100, 2110,
	public static final String BALANCE_ENQUIRY 					= "317878";
	public static final String SEND_RECEIPT						= "347878";
	public static final String TRANSACTION_REFUND				= "527878";
	
	/*	//2. User Administrative MTI : 2006, 2016
	public static final String USER_REGISTRATION 				= "7U7878";
	public static final String ADD_TRANSACTION_PASSWORD 		= "7V7878";
	public static final String VALIDATE_TRANSACTION_PASSWORD    = "7W7878";*/
	
	
	/*//2. User Administrative MTI : 2100, 2110,
	public static final String GET_TRANSACTION_HISTORY 			= "3S7878";
	
	 Electronic Purse Services 
	public static final String SEND_RECEIPT = "";
	public static final String SEND_MONEY = "";
	public static final String LOAD_MONEY = "";
	
	
	 Reserved 8S-8Z - Reserved for private use 
	public static final String ASSOCIATE_CARD 					= "8S7878";
	public static final String GET_FUNDING_SOURCE_LIST 			= "8T7878";
	public static final String ADD_FUNDING_SOURCE 				= "8U7878";
	public static final String ADD_RECEIVER 					= "8V7878";
	public static final String GET_CARD_LIST					= "8W7878";
	public static final String GET_CARD_INFO	 				= "8X7878";
	public static final String SET_PREFERRED_CARD 				= "8Y7878";
	
	
	 Transfer Services 
	 * 40	 - Cardholder accounts tranfer
	 * 4S-4Z - Reserved for private use
	 
	*/
	
}
