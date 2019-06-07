package com.landq.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.ApplicationException;
import com.landq.account.dao.IAccountDAO;
import com.landq.account.domain.Account;
import com.landq.account.service.AccountTransferRequest;
import com.landq.account.service.IAccountService;
import com.landq.account.service.IAuthenticationService;

@Component
public class AccountService implements IAccountService {

	@Autowired
	private IAccountDAO accountRepository;

	@Autowired
	private IAuthenticationService authenticationService;

	public void checkAccountDetails(String senderAcccount, String receiverAcccount) throws ApplicationException {

		Account senderAc = accountRepository.findByAccountNumber(senderAcccount);
		if (senderAc == null) {

			throw new ApplicationException("Invalid_Account_Sender");
		}

		Account receiverAc = accountRepository.findByAccountNumber(receiverAcccount);
		if (receiverAc == null) {

			throw new ApplicationException("Invalid_Account_Receiver");
		}
	}

	public void doTransfer(AccountTransferRequest accountTransferRequest) throws ApplicationException {

		String senderAccount = accountTransferRequest.getSenderAccount();
		String receiverAccount = accountTransferRequest.getReceiverAccount();
		Double transferAmount = accountTransferRequest.getTransferAmount();

		// Authenticate & authorize the User
		authenticationService.doAuthentication(accountTransferRequest.getUsername(),
				accountTransferRequest.getPassword());

		isPositiveAmount(transferAmount);

		// Validate the Account Details
		this.checkAccountDetails(senderAccount, receiverAccount);

		Account senderAc = accountRepository.findByAccountNumber(senderAccount);

		// Sufficient Balance Check
		isSufficientBalanceAvailable(transferAmount, senderAc);

		Account receiverAc = accountRepository.findByAccountNumber(receiverAccount);

		creditAccount(transferAmount, receiverAc);

		// debit the amount from Sender
		debitAccount(transferAmount, senderAc);

		// save the data to database
		accountRepository.save(receiverAc);
		accountRepository.save(senderAc);

	}

	private void debitAccount(Double transferAmount, Account senderAc) {
		Double finalSenderBalance = senderAc.getBalance() - transferAmount;
		senderAc.setBalance(finalSenderBalance);
	}

	private void creditAccount(Double transferAmount, Account receiverAc) {
		// credit the amount to receiver
		Double finalReceiverBalance = receiverAc.getBalance() + transferAmount;
		receiverAc.setBalance(finalReceiverBalance);
	}

	private void isSufficientBalanceAvailable(Double transferAmount, Account senderAc) throws ApplicationException {
		if (senderAc.getBalance() < transferAmount) {
			throw new ApplicationException("Insufficient_Balance");
		}
	}

	private void isPositiveAmount(Double transferAmount) throws ApplicationException {
		if (transferAmount <= 0) {
			throw new ApplicationException("Transfer_Amount_Negative");
		}
	}

}
