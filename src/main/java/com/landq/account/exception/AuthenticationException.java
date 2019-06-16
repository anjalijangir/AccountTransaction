package com.landq.account.exception;

import com.landq.account.ApplicationException;
/**
 * AuthenticationException arise when user is not authenticate to do transaction meant to user name and password
 * not authenticate.
 * 
 * @author Anjali
 *
 *@param message
 */
public class AuthenticationException extends ApplicationException {
	public AuthenticationException(String message) {
		super(message);
		
	}	
	private static final long serialVersionUID = 1L;
}
