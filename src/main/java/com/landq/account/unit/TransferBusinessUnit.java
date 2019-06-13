package com.landq.account.unit;



import org.springframework.beans.factory.annotation.Autowired;

import com.landq.account.dao.IAccountDAO;
import com.landq.account.domain.Account;
import com.landq.account.exception.BusinessException;
import com.landq.account.service.AccountTransferRequest;

/**
 * BusinessUnit Present here to perform business validation.
 * Here I am checking  sender account has  sufficient balance or not then throw business exception.
 * 
 * */
public class TransferBusinessUnit {

	@Autowired
	private IAccountDAO accountRepository;

	public void businessValidation(AccountTransferRequest accountTransferRequest) throws BusinessException {
		Account senderAccount = accountRepository.findByAccountNumber(accountTransferRequest.getSenderAccount());
		Account receiverAccount = accountRepository.findByAccountNumber(accountTransferRequest.getReceiverAccount());
		ensurePositiveAmount(accountTransferRequest.getTransferAmount());
		ensureSufficientBalanceAvailable(accountTransferRequest.getTransferAmount(), senderAccount);
		creditAccount(accountTransferRequest.getTransferAmount(), receiverAccount);
		debitAccount(accountTransferRequest.getTransferAmount(), senderAccount);		
		
	}
	
	

	/*
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
	
	/*
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
	

	/*
	 * ensureSufficientBalanceAvailable() method is used to check sender has sufficient
	 * balance or not.
	 * 
	 * @param transferAmount
	 * 
	 * @param senderAc
	 * 
	 * @throws BusinessException
	 */
	private void ensureSufficientBalanceAvailable(Double transferAmount, Account senderAc) throws BusinessException {
		if (senderAc.getBalance() < transferAmount) {
			throw new BusinessException("Insufficient_Balance");
		}
	}
	
	/*
	 * ensurePositiveAmount() method is used to check amount must be positive.
	 * balance or not.
	 * 
	 * @param transferAmount
	 * 
	 * @param senderAc
	 * 
	 * @throws BusinessException
	 */

	private void ensurePositiveAmount(Double transferAmount) throws BusinessException {
		if (transferAmount <= 0) {
			throw new BusinessException("Transfer_Amount_InValid");
		}
	}
}