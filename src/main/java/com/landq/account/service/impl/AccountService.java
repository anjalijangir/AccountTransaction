package com.landq.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.ApplicationException;
import com.landq.account.service.AccountTransferRequest;
import com.landq.account.service.IAccountService;
import com.landq.account.unit.TransferUnit;

/**
 * AccountService is a class which implements the IAccountService interface to
 * use functionality of this service.
 * 
 * @author Anjali
 *
 */
@Component
public class AccountService implements IAccountService {

	@Autowired
	private TransferUnit transferUnit;

	/**
	 * transfer money used to transfer the amount from one account to another
	 * account.
	 * 
	 * @param accountTransferRequest
	 * 
	 * @throws ApplicationException
	 */
	public void transferMoney(AccountTransferRequest accountTransferRequest) throws ApplicationException {

		transferUnit.transfer(accountTransferRequest);
	}
}
