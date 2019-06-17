package com.landq.account.unit;

import org.springframework.beans.factory.annotation.Autowired;

import com.landq.account.dao.IAccountDAO;
import com.landq.account.domain.Account;
import com.landq.account.exception.BusinessException;
import com.landq.account.service.AccountTransferRequest;

/**
 * BusinessUnit Present here to perform business validation. Here I am checking
 * following conditions to transfer money from one account to another account:-
 * 
 * ensurePositiveAmount--Checks amount positive or not then throw
 * "Transfer_Amount_InValid" exception.
 * 
 * ensureSufficientBalanceAvailable--Checks amount sufficient if not then throw
 * "Insufficient_Balance".
 * 
 * debitAccount--Debit the amount from sender to receiver.
 * 
 * creditAccount--Credit amount from sender to receiver.
 */
public class TransferBusinessUnit {

	@Autowired
	private IAccountDAO accountRepository;

	public void transfer(AccountTransferRequest accountTransferRequest) throws BusinessException {
		Account senderAccount = accountRepository.findByAccountNumber(accountTransferRequest.getSenderAccount());
		Account receiverAccount = accountRepository.findByAccountNumber(accountTransferRequest.getReceiverAccount());

		// Checks amount positive if not then throw "Transfer_Amount_InValid" exception.
		ensurePositiveAmount(accountTransferRequest.getTransferAmount());

		// Checks amount sufficient if not then throw "Insufficient_Balance".
		ensureSufficientBalanceAvailable(accountTransferRequest.getTransferAmount(), senderAccount);

		// Debits the amount from sender to receiver
		debitAccount(accountTransferRequest.getTransferAmount(), senderAccount);

		// Credit amount from sender to receiver
		creditAccount(accountTransferRequest.getTransferAmount(), receiverAccount);

	}

	/**
	 * Debit the amount from sender to receiver then checks finalSenderBalance after
	 * debited.
	 * 
	 * @param transferAmount
	 * 
	 * @param senderAc
	 */
	private void debitAccount(Double transferAmount, Account senderAc) {
		Double finalSenderBalance = senderAc.getBalance() - transferAmount;
		senderAc.setBalance(finalSenderBalance);
		accountRepository.save(senderAc);
	}

	/**
	 * Credit the amount for receiver and generate finalReceiverBalance after
	 * credited.
	 * 
	 * @param transferAmount
	 * 
	 * @param receiverAc
	 */

	private void creditAccount(Double transferAmount, Account receiverAc) {
		Double finalReceiverBalance = receiverAc.getBalance() + transferAmount;
		receiverAc.setBalance(finalReceiverBalance);
		accountRepository.save(receiverAc);
	}

	/**
	 * ensureSufficientBalanceAvailable() method is used to check sender has
	 * sufficient balance or not.
	 * 
	 * @param transferAmount
	 * 
	 * @param senderAc
	 * 
	 * @throws BusinessException--When the Business rule for the Transfer is failed
	 *                                 (refer the class level comments
	 */
	private void ensureSufficientBalanceAvailable(Double transferAmount, Account senderAc) throws BusinessException {
		if (senderAc.getBalance() < transferAmount) {
			throw new BusinessException("Insufficient_Balance");
		}
	}

	/**
	 * ensurePositiveAmount() method is used to check amount must be positive.
	 * balance or not.
	 * 
	 * @param transferAmount
	 * 
	 * @param senderAc
	 * 
	 * @throws BusinessException--When the Business rule for the Transfer is failed
	 *                                 (refer the class level comments
	 */

	private void ensurePositiveAmount(Double transferAmount) throws BusinessException {
		if (transferAmount <= 0) {
			throw new BusinessException("Transfer_Amount_InValid");
		}
	}
}
