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
	
	@Test
	public void testUserNameNull()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(null);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount(VALID_RECEIVER_ACCOUNT_NUMBER);
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("UserName_Cannot_Be_Null_Or_Empty", exception.getMessage());

		}
	}
	
	@Test
	public void testUserNameEmpty()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername("");
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount(VALID_RECEIVER_ACCOUNT_NUMBER);
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("UserName_Cannot_Be_Null_Or_Empty", exception.getMessage());

		}
	}
	
	@Test
	public void testPasswordNull()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(null);
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount(VALID_RECEIVER_ACCOUNT_NUMBER);
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Password_Cannot_Be_Null_Or_Empty", exception.getMessage());

		}
	}
	
	@Test
	public void testPasswordEmpty()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword("");
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount(VALID_RECEIVER_ACCOUNT_NUMBER);
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Password_Cannot_Be_Null_Or_Empty", exception.getMessage());

		}
	}
	
	@Test
	public void testSenderAccountNumberNull()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount(null);
		accountTransferRequest.setReceiverAccount(VALID_RECEIVER_ACCOUNT_NUMBER);
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Sender_Account_Cannot_Be_Null_Or_Empty", exception.getMessage());

		}
	}
	
	@Test
	public void testSenderAccountNumberEmpty()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("");
		accountTransferRequest.setReceiverAccount(VALID_RECEIVER_ACCOUNT_NUMBER);
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Sender_Account_Cannot_Be_Null_Or_Empty", exception.getMessage());

		}
	}
	
	@Test
	public void testReceiverAccountNumberNull()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount(null);
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Receiver_Account_Cannot_Be_Null_Or_Empty", exception.getMessage());

		}
	}
	
	@Test
	public void testReceiverAccountNumberEmpty()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount("");
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Receiver_Account_Cannot_Be_Null_Or_Empty", exception.getMessage());

		}
	}
	
	@Test
	public void testSenderAccountNumberLength()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("1234567");
		accountTransferRequest.setReceiverAccount(VALID_RECEIVER_ACCOUNT_NUMBER);
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Invalid_Sender_And_Receiver_AccountNumber", exception.getMessage());

		}
	}
	
	@Test
	public void testReceiverAccountNumberLength()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount("56789054");
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Invalid_Sender_And_Receiver_AccountNumber", exception.getMessage());

		}
	}
	
	@Test
	public void testSenderAccountNumberPattern()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("&*546abc");
		accountTransferRequest.setReceiverAccount(VALID_RECEIVER_ACCOUNT_NUMBER);
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Invalid_Sender_And_Receiver_AccountNumber", exception.getMessage());

		}
	}
	
	@Test
	public void testReceiverAccountNumberPattern()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount("*654rt");
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Invalid_Sender_And_Receiver_AccountNumber", exception.getMessage());

		}
	}
	
	@Test
	public void testSenderAndReceiverAccountNumberSame()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("123456");
		accountTransferRequest.setReceiverAccount("123456");
		accountTransferRequest.setTransferAmount(400.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Invalid_Sender_And_Receiver_AccountNumber", exception.getMessage());

		}
	}
	
	@Test
	public void testTransferAmountNull()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("123456");
		accountTransferRequest.setReceiverAccount("123456");
		accountTransferRequest.setTransferAmount(null);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("transferAmount_Cannot_Be_Null", exception.getMessage());

		}
	}
	
	@Test
	public void testTransferAmountZero()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("123456");
		accountTransferRequest.setReceiverAccount("123456");
		accountTransferRequest.setTransferAmount(0.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("transferAmount_Cannot_Be_Negative_Or_Zero", exception.getMessage());

		}
	}
	
	@Test
	public void testTransferAmountNegative()  {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("123456");
		accountTransferRequest.setReceiverAccount("123456");
		accountTransferRequest.setTransferAmount(-100.0);		
		try {
			transferValidationUnit.validate(accountTransferRequest);
			Assert.fail("Exception_Expected");
		} catch (ValidationException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("transferAmount_Cannot_Be_Negative_Or_Zero", exception.getMessage());

		}
	}
	
	
	
	
}
