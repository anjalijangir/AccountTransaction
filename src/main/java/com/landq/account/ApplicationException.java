package com.landq.account;

public class ApplicationException extends Exception {
/**
 * ApplicationException meant to if any exception is arise in this AccountTRansaction Application 
 * then we are giving some message for alert.
 * @param message
 */
	public ApplicationException(String message) {
		super(message);
		
	}	
	private static final long serialVersionUID = 1L;
	
}
