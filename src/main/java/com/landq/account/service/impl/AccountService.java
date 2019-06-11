package com.landq.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.ApplicationException;
import com.landq.account.service.AccountTransferRequest;
import com.landq.account.service.IAccountService;
import com.landq.account.unit.TransferUnit;

@Component
public class AccountService implements IAccountService {
	
	@Autowired
	private TransferUnit transferUnit;
	
	public void transferMoney(AccountTransferRequest accountTransferRequest) throws ApplicationException {
		
		transferUnit.transfer(accountTransferRequest);
	}
}
