package com.landq.account.exception;

import com.landq.account.ApplicationException;

public class AuthenticationException extends ApplicationException {
	public AuthenticationException(String message) {
		super(message);
		
	}	
	private static final long serialVersionUID = 1L;
}
