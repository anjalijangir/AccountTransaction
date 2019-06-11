package com.landq.account.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.ApplicationException;
import com.landq.account.dao.IAccountDAO;
import com.landq.account.domain.Account;
import com.landq.account.exception.BusinessException;
import com.landq.account.service.AccountTransferRequest;
/**
 * 1. In this unit we are fetching IAccountDAO database for transfer the amount from sender to receiver
 * 2. Transfer amount isPositive. 
 * 3. checking Sender and Receiver Account Details is present in UserAccountDetails. 
 * 4. checking weather sender having sufficient balance. 
 * 5. If sender having sufficient balance then it will debit from sender credit to the receiver. 
 * 6. Update the amount details from both side.
 */

@Component
public class TransferUnit {

	@Autowired/**It will inject the bean for  IAccountDAO .*/
	private IAccountDAO accountRepository;

	@Autowired/**It will inject the bean for AuthenticationUnit*/
	private AuthenticationUnit authenticationUnit;
	
	
    /**
     * checkAccountDetails() method checks the validation for sender meant to sender is validate to do transaction<br>
     * @param senderAcccount
     * @param receiverAcccount
     * @throws ApplicationException
     */
	private void checkAccountDetails(String senderAcccount, String receiverAcccount) throws ApplicationException {
		
		// Finds sender account details from the database by given the account number
		Account senderAc = accountRepository.findByAccountNumber(senderAcccount);
		if (senderAc == null) {

			throw new ApplicationException("Invalid_Account_Sender");
		}
		
		// Finds receiver account details from the database by given the account number
		Account receiverAc = accountRepository.findByAccountNumber(receiverAcccount);
		if (receiverAc == null) {

			throw new ApplicationException("Invalid_Account_Receiver");
		}
	}
	
	/**
	 * transfer() method is used to transfer the amount from sender to receiver.
	 * @param accountTransferRequest
	 * @param senderAccount
	 * @param receiverAccount
	 * @param transferAmount
	 * @throws ApplicationException
	 * 
	 */
	public void transfer(AccountTransferRequest accountTransferRequest) throws ApplicationException {

		if (accountTransferRequest == null ) {
			throw new ApplicationException("Input_is_null");
		}
		
		// Authenticate & authorize the User
		authenticationUnit.doAuthentication(accountTransferRequest.getUsername(),accountTransferRequest.getPassword());

		String senderAccount = accountTransferRequest.getSenderAccount();
		String receiverAccount = accountTransferRequest.getReceiverAccount();
		Double transferAmount = accountTransferRequest.getTransferAmount();
		
		isPositiveAmount(transferAmount);

		// Validate the Account Details
		this.checkAccountDetails(senderAccount, receiverAccount);

		Account senderAc = accountRepository.findByAccountNumber(senderAccount);

		// Sufficient Balance Check
		isSufficientBalanceAvailable(transferAmount, senderAc);

		Account receiverAc = accountRepository.findByAccountNumber(receiverAccount);

		creditAccount(transferAmount, receiverAc);

		// Debit the amount from Sender
		debitAccount(transferAmount, senderAc);

		// save the data to database
		accountRepository.save(receiverAc);
		accountRepository.save(senderAc);

	}
	/**
	 * Debit the amount from sender to receiver then checks finalSenderBalance after debited.
	 * @param transferAmount
	 * @param senderAc
	 */
	private void debitAccount(Double transferAmount, Account senderAc) {
		Double finalSenderBalance = senderAc.getBalance() - transferAmount;
		senderAc.setBalance(finalSenderBalance);
	}
	
	/**
	 * Credit the amount for receiver and generate finalReceiverBalance after credited.
	 * @param transferAmount
	 * @param receiverAc
	 */
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
	 */
	private void isSufficientBalanceAvailable(Double transferAmount, Account senderAc) throws BusinessException {
		if (senderAc.getBalance() < transferAmount) {
			throw new BusinessException("Insufficient_Balance");
		}
	}

	private void isPositiveAmount(Double transferAmount) throws ApplicationException {
		if (transferAmount <= 0) {
			throw new BusinessException("Transfer_Amount_InValid");
		}
	}

}
