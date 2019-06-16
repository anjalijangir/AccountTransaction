package com.landq.account.service;

import com.landq.account.ApplicationException;
/**
 * IAccountService is a interface which represent which contains transferMoney method deceleration.
 * Service means what is main purpose.  
 * 
 * @author Anjali
 *
 */
public interface IAccountService {

	public void transferMoney(AccountTransferRequest accountTransferRequest) throws ApplicationException;

}
