package com.landq.account.service;

import com.landq.account.ApplicationException;

public interface IAccountService {

	public void transferMoney(AccountTransferRequest accountTransferRequest) throws ApplicationException;

}
