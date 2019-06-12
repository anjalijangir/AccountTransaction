package com.landq.account.unit;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.landq.account.domain.Account;
import com.landq.account.exception.BusinessException;
import com.landq.account.exception.ValidationException;
import com.landq.account.service.AccountTransferRequest;
@Autowired
private IAccountDAO accountRepository
public class ValidationUnit {
	
	public void performManadatoryValidation(AccountTransferRequest accountTransferRequest) {
		
		String senderAccount = accountTransferRequest.getSenderAccount();
		String receiverAccount = accountTransferRequest.getReceiverAccount();
		
		Double transferAmount = accountTransferRequest.getTransferAmount();
		checkAccountValidation(String senderAcccount, String receiverAcccount);
		checkUserAndPasswordValidation();
	}
	
	 private void checkAccountValidation(String senderAcccount, String receiverAcccount) {
		 
		// Checks sender account if null or empty then throw AuthenticationException
			if (senderAcccount == null || senderAcccount.isEmpty()) {
				throw new ValidationException("Invalid_Sender_Account");
			}
			

			// Checks Receiver account if null or empty then throw AuthenticationException
			if (receiverAcccount == null || receiverAcccount.isEmpty()) {
				throw new ValidationException("Invalid_Receiver_Account");
			}

			// Finds sender account details from the database by given the account number
			Account senderAc = accountRepository.findByAccountNumber(senderAcccount);
			if (senderAc == null) {
				throw new ValidationException("Invalid_Sender_AccountNumber");
			}

			// Finds receiver account details from the database by given the account number
			Account receiverAc = accountRepository.findByAccountNumber(receiverAcccount);
			if (receiverAc == null) {
				throw new ValidationException("Invalid_Receiver_AccountNumber");
			}

			// Checks if Sender and Receiver account same then throw
			// Invalid_Sender_And_Receiver_AccountNumber exception
			if (senderAc.equals(receiverAc)) {
				throw new ValidationException("Invalid_Sender_And_Receiver_AccountNumber");
			}

			if (senderAcccount.length() != 16 || receiverAcccount.length() != 16) {
				throw new ValidationException("Invalid_Sender_And_Receiver_AccountNumber");
			}
		}
}
	/**
	 * Debit the amount from sender to receiver then checks finalSenderBalance after debited.
	 * @param transferAmount
	 * @param senderAc
	 
		String senderAccount = accountTransferRequest.getSenderAccount();
		String receiverAccount = accountTransferRequest.getReceiverAccount();
		Double transferAmount = accountTransferRequest.getTransferAmount();
		
	private void debitAccount(Double transferAmount, Account senderAc) {
		Double finalSenderBalance = senderAc.getBalance() - transferAmount;
		senderAc.setBalance(finalSenderBalance);
	}
	
	/**
	 * Credit the amount for receiver and generate finalReceiverBalance after credited.
	 * @param transferAmount
	 * @param receiverAc
	 
	private void creditAccount(Double transferAmount, Account receiverAc) {
		// credit the amount to receiver
		Double finalReceiverBalance = receiverAc.getBalance() + transferAmount;
		receiverAc.setBalance(finalReceiverBalance);
	}
	
	
	/**
	 * isSufficientBalanceAvailable() method is used to check sender has sufficient balance or not. 
	 * @param transferAmount
	 * @param senderAc
	 * @throws BusinessException
	 
	private void ensureSufficientBalanceAvailable(Double transferAmount, Account senderAc) throws BusinessException {
		if (senderAc.getBalance() < transferAmount) {
			throw new BusinessException("Insufficient_Balance");
		}
	}

	
	private void ensurePositiveAmount(Double transferAmount) throws BusinessException {
		if (transferAmount <= 0) {
			throw new BusinessException("Transfer_Amount_InValid");
		}
	}*/
	 
