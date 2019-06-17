package com.landq.account.service;

import com.landq.account.ApplicationException;

/**
 * IAccountService is a interface which represent which contains transferMoney
 * method deceleration. Service means what is main purpose.
 * 
 * @author Anjali
 *
 */
public interface IAccountService {
	/**
	 * transfer money used to transfer the amount from one account to another
	 * account.
	 * 
	 * @param accountTransferRequest
	 * 
	 * @throws ApplicationException
	 */
	public void transferMoney(AccountTransferRequest accountTransferRequest) throws ApplicationException;

}
