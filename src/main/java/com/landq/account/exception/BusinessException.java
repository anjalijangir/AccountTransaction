package com.landq.account.exception;

import com.landq.account.ApplicationException;
/**
 *BusinessException arise when i want do some transaction and i do not have sufficient balance for transfer amount
 *then business exception will thrown.
 *
 *@param message
 *
 */
public class BusinessException extends ApplicationException {
	public BusinessException(String message) {
		super(message);		
	}	
	private static final long serialVersionUID = 1L;
}
