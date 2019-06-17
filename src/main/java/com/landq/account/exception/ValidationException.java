package com.landq.account.exception;

import com.landq.account.ApplicationException;
/**
 * ValidationException arise when user is not able to transaction means user is not validate like:
 * 
 * User name and password Null
 * 
 * User name password Empty
 * 
 * Account number null or empty
 */
public class ValidationException extends ApplicationException {
	public ValidationException(String message) {
		super(message);		
	}	
	private static final long serialVersionUID = 1L;
}
