package com.landq.account.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.ApplicationException;
import com.landq.account.exception.AuthenticationException;
import com.landq.account.exception.BusinessException;
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

	
	@Autowired // It will inject the bean for AuthenticationUnit
	private TransferAuthenticationUnit authenticationUnit;
	
	@Autowired
	private TransferValidationUnit validationUnit;
	
	@Autowired
	private TransferBusinessUnit businessUnit;
	

	
	
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
		authenticationUnit.authenticate(accountTransferRequest.getUsername(), accountTransferRequest.getPassword());
		//Validate the transaction
		validationUnit.validate(accountTransferRequest);
		// Validate business 
		businessUnit.businessValidation( accountTransferRequest);
		

		
		
		

	}

	
}

