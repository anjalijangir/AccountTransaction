package com.landq.account.exception;

import com.landq.account.ApplicationException;
/**
 * If in this AccountTransaction Application has some transaction like-i am transfer amount from one account to another 
 * account and i do not have sufficient balance then BusinessException will be thrown.
 * 
 *@param message
 */
public class BusinessException extends ApplicationException {
	public BusinessException(String message) {
		super(message);		
	}	
	private static final long serialVersionUID = 1L;
}
