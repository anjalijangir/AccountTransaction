package com.landq.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.domain.Account;
import com.landq.account.repository.AccountRepository;
import com.landq.account.service.IAccountService;

@Component
public class AccountService implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	public boolean checkAccountDetails(String senderAcccount, String receiverAcccount) {
		boolean isSuccess = true;
		Account senderAc = accountRepository.findByAccountNumber(senderAcccount);
		if (senderAc == null) {
			System.out.println("Invalid sender account number : " + senderAcccount);
			isSuccess = false;
		}
		Account receiverAc = accountRepository.findByAccountNumber(receiverAcccount);
		if (receiverAc == null) {
			System.out.println("Invalid receiver account number : " + receiverAcccount);
			isSuccess = false;
		}
		return isSuccess;
	}

	public void doTransfer(String senderAccount, String receiverAccount, Double transferAmount) {
		Account receiverAc = accountRepository.findByAccountNumber(receiverAccount);
		// credit the amount to receiver
		Double finalReceiverBalance = receiverAc.getBalance() + transferAmount;
		receiverAc.setBalance(finalReceiverBalance);
		// save the data to database
		accountRepository.save(receiverAc);
		System.out.println(receiverAc);

		Account senderAc = accountRepository.findByAccountNumber(senderAccount);
		// debit the amount to receiver
		Double finalSenderBalance = senderAc.getBalance() - transferAmount;
		senderAc.setBalance(finalSenderBalance);
		// save the data to database
		accountRepository.save(senderAc);
		System.out.println(senderAc);
		System.out.println("Payment transfer successfull.");
	}

}
