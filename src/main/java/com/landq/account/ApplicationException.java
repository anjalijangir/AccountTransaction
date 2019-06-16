package com.landq.account;
/**
 * ApplicationException meant to if any exception is arise in this Account TRansaction Application 
 * then we are giving some message for alert.
 */
public class ApplicationException extends Exception {

	public ApplicationException(String message) {
		super(message);
		
	}	
	private static final long serialVersionUID = 1L;
	
}
