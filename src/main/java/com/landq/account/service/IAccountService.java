package com.landq.account.service;

import com.landq.account.ApplicationException;

public interface IAccountService {

	public void checkAccountDetails(String senderAcccount, String receiverAcccount) throws ApplicationException;

	public void doTransfer(AccountTransferRequest accountTransferRequest) throws ApplicationException;

}
