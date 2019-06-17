package com.landq.account;
/**
 * Application exception handles application level exception.
 */
public class ApplicationException extends Exception {
	
	public ApplicationException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
