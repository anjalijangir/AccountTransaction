package com.landq.account.unit;

import org.junit.Assert;
import org.junit.Test;

import com.landq.account.exception.AuthenticationException;
import com.landq.account.exception.ValidationException;
import com.landq.account.service.AccountTransferRequest;
/**
 *  TransferValidationUnitTest class used for test the Valid or InValid scenario<br>
 * between sender and receiver. This TestCase covered followin
 * 
 *  @ValidationException
 * 
 *                      1.testUserNameEmpty--Checks if user name is empty then
 *                      throw "InValid_User_Name" ValidationException
 * 
 *                      2.testUserNameNull--Checks if user name is null then
 *                      throw "InValid_User_Name" ValidationException
 * 
 *                      3.testUserNameMisMatch--Checks if user name is mismatch
 *                      then throw "InValid_User_Name" ValidationException
 * 
 *                      4.testPasswordNull--Checks if password is null then
 *                      throw "InValid_Password" ValidationException
 * 
 *                      5.testPasswordMisMatch--Checks if password is null then
 *                      throw "InValid_Password" ValidationException
 * 
 *                      6.testPasswordEmpty--Checks if password is null then
 *                      throw "InValid_Password" ValidationException
 * 
 *                      7.testBothUserNameAndPasswordNull---Checks if both user
 *                      name and password are null then throw
 *                      "InValid_UserName_And_Password" ValidationException
 * 
 *                      8.TestBothUserNameandPasswordEmpty--Checks if both user
 *                      name and password are empty then throw
 *                      "InValid_UserName_And_Password" ValidationException
 * 
 *                      9.testBothUserNameAndPasswordMisMatch--Checks if both
 *                      user name and password are mismatch then throw
 *                      "InValid_UserName_And_Password" ValidationException
 * 
 *                      10.testSenderAccountNull--Checks if sender account is
 *                      null then throw "InValid_Sender_Account".
 * 
 *                      11.testSenderAccountEmpty--Checks if sender account is
 *                      empty then throw "InValid_Account".
 * 
 *                      12.testSenderAccountMisMatch--Checks if sender account
 *                      is mismatch then throw "InValid_Account".
 * 
 *                      13.testReceiverAccountNull--Checks if receiver account
 *                      is null then throw "InValid_Account".
 *                      14.testReceiverAccountEmpty--Checks if receiver account
 *                      is empty then throw "InValid_Receiver_Account".
 * 
 *                      15.testReceiverAccountMisMatch--Checks if receiver
 *                      account mismatch then throw "InValid_Receiver_Account".
 * 
 *                      16.testBothSenderAndReceiverAccountEmpty--Checks if both
 *                      sender and receiver account empty then throw
 *                      "InValid_Receiver_And_Sender_Account".
 * 
 *                      17.testBothSenderAndReceiverAccountNull--Checks if both
 *                      sender and receiver account null then throw
 *                      "InValid_Receiver_And_Sender_Account".
 * 
 *                      18.testBothSenderAndReceiverAccountMisMatch--Checks if
 *                      both sender and receiver account mismatch then throw
 *                      "InValid_Receiver_And_Sender_Account".
 * 
 *                      19.testBothSenderAndReceiverAccountSame--Checks if both
 *                      sender and receiver accounts are same then throw
 *                      "InValid_Receiver_And_Sender_Account".
 *                      
 *                      20.testSenderAndReceiverAccountNumberPatternValidation--checks
 *                      if Account holder's account number in valid pattern or not then throw
 *                      "Invalid_Sender_And_Receiver_AccountNumber" exception.
 *                      
 */
 
public class TransferValidationUnitTest {

	private final String VALID_USERNAME = "anjali123";
	private final String VALID_PASSWORD = "admin";
	private final String VALID_SENDER_ACCOUNT_NUMBER="123456";
	private final String VALID_RECEIVER_ACCOUNT_NUMBER="987654";
	
	TransferValidationUnit transferValidationUnit=new TransferValidationUnit();
	
	/**
	 * This scenario checks whether given Account holder's name and details are validate successfully
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected user name password account details input and actual same if not then 
	 * it will throw exception "Found_Exception".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	
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
	
	/**
	 * This scenario checks whether given user name null or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected user name  input and actual output same if not then 
	 * it will throw exception "UserName_Cannot_Be_Null_Or_Empty".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	
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
	
	/**
	 * This scenario checks whether given user name Empty or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected user name  input and actual output same or not if not then 
	 * it will throw exception "UserName_Cannot_Be_Null_Or_Empty".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
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
	
	/**
	 * This scenario checks whether given password null or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected password  input and actual output same or not if not then 
	 * it will throw exception "Password_Cannot_Be_Null_Or_Empty".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	
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
	
	/**
	 * This scenario checks whether given password Empty or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected password  input and actual output same or not if not then 
	 * it will throw exception "Password_Cannot_Be_Null_Or_Empty".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	
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
	
	/**
	 * This scenario checks whether sender account number null or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected sender account number  input and actual output same or not if not then 
	 * it will throw exception "Sender_Account_Cannot_Be_Null_Or_Empty".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	
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
	/**
	 * This scenario checks whether sender account number Empty or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected sender account number  input and actual output same or not if not then 
	 * it will throw exception "Sender_Account_Cannot_Be_Null_Or_Empty".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	
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
	
	/**
	 * This scenario checks whether receiver account number null or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected receiver account number  input and actual output same or not if not then 
	 * it will throw exception"Receiver_Account_Cannot_Be_Null_Or_Empty".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
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
	/**
	 * This scenario checks whether receiver account number empty or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected receiver account number  input and actual output same or not if not then 
	 * it will throw exception"Receiver_Account_Cannot_Be_Null_Or_Empty".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	
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
	
	/**
	 * This scenario checks whether sender account number has valid length or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected receiver account number  input and actual output same or not if not then 
	 * it will throw exception"Invalid_Sender_And_Receiver_AccountNumber".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
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
	
	/**
	 * This scenario checks whether receiver account number has valid length or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected receiver account number  input and actual output same or not if not then 
	 * it will throw exception"Invalid_Sender_And_Receiver_AccountNumber".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
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
	
	/**
	 * This scenario checks whether sender account number has valid pattern or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected receiver account number  input and actual output same or not if not then 
	 * it will throw exception"Invalid_Sender_And_Receiver_AccountNumber".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
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
	
	/**
	 * This scenario checks whether receiver account number has valid pattern or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected receiver account number  input and actual output same or not if not then 
	 * it will throw exception"Invalid_Sender_And_Receiver_AccountNumber".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
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
	
	/**
	 * This scenario checks whether sender's and receiver's account number same or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected sender and receiver account number  input and actual output same or not then 
	 * it will throw exception"Invalid_Sender_And_Receiver_AccountNumber".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
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
	
	/**
	 * This scenario checks whether transfer amount null or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected transfer amount  input and actual output same or not then 
	 * it will throw exception"transferAmount_Cannot_Be_Null".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
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
	
	/**
	 * This scenario checks whether transfer amount zero or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected transfer amount  input and actual output same or not then 
	 * it will throw exception"transferAmount_Cannot_Be_Negative_Or_Zero".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
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
	

	/**
	 * This scenario checks whether transfer amount negative or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws ValidationException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected transfer amount  input and actual output same or not then 
	 * it will throw exception"transferAmount_Cannot_Be_Negative_Or_Zero".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	@Test
	public void testTransferAmountNegative() {
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
