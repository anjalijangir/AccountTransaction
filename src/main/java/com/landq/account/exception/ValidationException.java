package com.landq.account.exception;

import com.landq.account.ApplicationException;
/**
 * ValidationException arise when user is not able to transaction means user is not authorize.
 * @param message
 *
 */
public class ValidationException extends ApplicationException {
	public ValidationException(String message) {
		super(message);		
	}	
	private static final long serialVersionUID = 1L;
}
