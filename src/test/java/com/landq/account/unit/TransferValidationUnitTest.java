package com.landq.account.unit;

import org.junit.Assert;
import org.junit.Test;

import com.landq.account.exception.ValidationException;
import com.landq.account.service.AccountTransferRequest;

public class TransferValidationUnitTest {

	private final String VALID_USERNAME = "anjali123";
	private final String VALID_PASSWORD = "admin";
	private final String VALID_SENDER_ACCOUNT_NUMBER="123456";
	private final String VALID_RECEIVER_ACCOUNT_NUMBER="987654";
	
	TransferValidationUnit transferValidationUnit=new TransferValidationUnit();
	@Test
	public void testValidationSuccess()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount(VALID_RECEIVER_ACCOUNT_NUMBER);
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
		} catch (ValidationException e) {
			Assert.fail("Found_Exception");
		}
	}
}
