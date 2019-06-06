package com.landq.account.service;

public interface IAccountService {

	public boolean checkAccountDetails(String senderAcccount, String receiverAcccount);

	public void doTransfer(String senderAccount, String receiverAccount, Double transferAmount);

}
