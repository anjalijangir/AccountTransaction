package com.landq.account.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.ApplicationException;
import com.landq.account.dao.IAccountDAO;
import com.landq.account.domain.Account;
import com.landq.account.exception.AuthenticationException;
import com.landq.account.exception.BusinessException;
import com.landq.account.exception.ValidationException;
import com.landq.account.service.AccountTransferRequest;

/**
 * 1. In this unit we are fetching IAccountDAO database for transfer the amount
 * from sender to receiver 2. Transfer amount isPositive. 3. checking Sender and
 * Receiver Account Details is present in UserAccountDetails. 4. checking
 * weather sender having sufficient balance. 5. If sender having sufficient
 * balance then it will debit from sender credit to the receiver. 6. Update the
 * amount details from both side.
 */

@Component
public class TransferUnit {

	@Autowired // It will inject the bean for IAccountDAO
	private IAccountDAO accountRepository;

	@Autowired // It will inject the bean for AuthenticationUnit
	private AuthenticationUnit authenticationUnit;

	/**
	 * checkAccountDetails() method checks the validation for sender and Receiver
	 * meant to sender is validate to do transaction<br>
	 * 
	 * @param senderAcccount
	 * @param receiverAcccount
	 * @throws ApplicationException
	 */
	private void checkAccountDetails(String senderAcccount, String receiverAcccount)
			throws ApplicationException, BusinessException, AuthenticationException {
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

	/**
	 * transfer() method is used to transfer the amount from sender to receiver.
	 * @param accountTransferRequest
	 * @param senderAccount
	 * @param receiverAccount
	 * @param transferAmount
	 * @throws ApplicationException
	 * 
	 */
	public void transfer(AccountTransferRequest accountTransferRequest) throws ApplicationException, BusinessException,AuthenticationException {
		
		// Authenticate & authorize the User	
		authenticationUnit.performManadatoryAuthentication(accountTransferRequest.getUsername(),accountTransferRequest.getPassword())
		//Validate the transaction
		authenticationUnit.performManadatoryTransferValidation();
		

		
		
		ensurePositiveAmount(transferAmount);

		// Validate the Account Details
		this.checkAccountDetails(senderAccount, receiverAccount);

		Account senderAc = accountRepository.findByAccountNumber(senderAccount);

		// Sufficient Balance Check
		ensureSufficientBalanceAvailable(transferAmount, senderAc);

		Account receiverAc = accountRepository.findByAccountNumber(receiverAccount);

		creditAccount(transferAmount, receiverAc);

		// Debit the amount from Sender
		debitAccount(transferAmount, senderAc);

		// save the data to database
		accountRepository.save(receiverAc);
		accountRepository.save(senderAc);

	}

	
}
