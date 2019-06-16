package com.landq.account.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.exception.AuthenticationException;
import com.landq.account.exception.BusinessException;
import com.landq.account.exception.ValidationException;
import com.landq.account.service.AccountTransferRequest;

/**
 * Transfer Unit is responsible for transfer the given amount from sender account to receiver account.
 * 
 * The following are Validation and business rules related to Transfer
 * 
 * 	1. Both Sender and Receiver Accounts are mandatory
 *  2. Sender and Receiver Accounts should be two different account (cannot be the same account number)
 *  3. The Sender Account should have enough balance to Transfer the Money
 *  4. There is no minimum balance maintenance required. (meaning it can have zero balance)
 *  
 *  Note: The common rule, the user who is transferring must be authenticated.
 */
@Component
public class TransferUnit {

	@Autowired
	private TransferAuthenticationUnit authenticationUnit;
	
	@Autowired
	private TransferValidationUnit validationUnit;
	
	@Autowired
	private TransferBusinessUnit businessUnit;
	
	/**
	 * transfer() method is used to transfer the amount from sender to receiver.
	 * 
	 * @param accountTransferRequest
	 * 
	 * @throws AuthenticationException - When the user authentication failed
	 * @throws ValidationException - When the input validation fails
	 * @throws BusinessException - When the Business rule for the Transfer is failed (refer the class level comments
	 */
	public void transfer(AccountTransferRequest accountTransferRequest) 
			throws AuthenticationException, ValidationException, BusinessException {
		
		// Authenticate & authorize the User	
		authenticationUnit.authenticate(accountTransferRequest.getUsername(), accountTransferRequest.getPassword());
		//Validate the transaction
		validationUnit.validate(accountTransferRequest);
		// Validate business 
		businessUnit.transfer(accountTransferRequest);
	}
}

