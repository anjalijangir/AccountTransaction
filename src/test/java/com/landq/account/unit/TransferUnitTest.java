package com.landq.account.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.landq.account.ApplicationException;
import com.landq.account.dao.IAccountDAO;
import com.landq.account.exception.AuthenticationException;
import com.landq.account.exception.BusinessException;
import com.landq.account.exception.ValidationException;
import com.landq.account.service.AccountTransferRequest;

/**
 * TransferUnitTest class used for test the Valid or InValid scenario<br>
 * between sender and receiver. This TestCase covered following scenarios.<br>
 * 
 * @AuthenticationException
 *
 *                          1.testUserNameNull--check If User name is null then
 *                          throw the exception {@link AuthenticationException}
 *                          "Invalid_userName_and_password".<br>
 * 
 *                          2.testUserNameEmpty---check If User name is empty
 *                          then throw the exception
 *                          {@link AuthenticationException}
 *                          "Invalid_userName_and_password".<br>
 * 
 *                          3.testUserNameMisMatch--Checks if user name is wrong
 *                          one then throw the exception
 *                          {@link AuthenticationException}
 *                          "Invalid_userName_and_password".<br>
 * 
 *                          4.testPasswordNull--Checks if password is null then
 *                          throw "Invalid_userName_and_password"
 *                          {@link AuthenticationException} exception.
 * 
 *                          5.testPasswordMisMatch--Checks if i given wrong
 *                          password then it will throw
 *                          "Invalid_userName_and_password"
 *                          {@link AuthenticationException} exception.
 * 
 *                          6.testPasswordEmpty--Checks if i given empty
 *                          password then it will throw
 *                          "Invalid_userName_and_password"
 *                          {@link AuthenticationException} exception.
 * 
 *                          7.testBothUserNameAndPasswordNull--Checks if i given
 *                          null password then it will throw
 *                          "Invalid_userName_and_password"
 *                          {@link AuthenticationException} exception.
 * 
 *                          8.testBothUserNameandPasswordEmpty--Checks if i
 *                          given wrong password then it will throw
 *                          "Invalid_userName_and_password"
 *                          {@link AuthenticationException} exception.
 * 
 *                          9.testBothUserNameAndPasswordMisMatch--Checks if i
 *                          given wrong password then it will throw
 *                          "Invalid_userName_and_password"
 *                          {@link AuthenticationException} exception.
 * 
 * @BusinessException
 * 
 *                    1.ensureTransferAmountZero---Checks if sender has zero
 *                    balance then throw "Transfer_Amount_InValid"
 *                    BusinessException.
 * 
 *                    2.ensureTransferAmountNegative---Checks if transfer amount
 *                    is negative then throw "Transfer_Amount_Negative"
 *                    exception<br>
 * 
 * @ValidationException
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
 */

public class TransferUnitTest {
	@Mock /** will create a mock implementation for the IAccountDAO */
	private IAccountDAO accountRepository;

	@Mock /** will create a mock implementation for the AuthenticationUnit */
	private AuthenticationUnit authenticationUnit;

	@InjectMocks /**
					 * will inject the mocks marked with @Mock to this instance when it is created.
					 */
	TransferUnit transferUnit;

	/**
	 * For the testing purpose here i am giving VALID_USERNAME and VALID_PASSWORD as
	 * a constant with Final keyword.
	 */
	private final String VALID_USERNAME = "anjali";
	private final String VALID_PASSWORD = "admin";

	@Before /** It will initialize a mock instance for TransferUnit and transferUnit */
	public void init() {
		transferUnit = new TransferUnit();
		MockitoAnnotations.initMocks(this);

	}

	/**
	 * This test case checks if all inputs are null then throw exception
	 * "Input_is_null".
	 * 
	 * @author Anjali
	 * 
	 *         Data set:<br>
	 *         1.AccountTransferRequest.<br>
	 * 
	 * @throws AuthenticationException
	 * 
	 *                                 Verification :<br>
	 *                                 1. Here i am checking if expected input and
	 *                                 actual input not same then it will throw
	 *                                 exception .<br>
	 *                                 2. Verifying it's fail or not.<br>
	 */
	@Test
	public void testInputsEmpty() throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("123456");
		accountTransferRequest.setReceiverAccount("423456");
		accountTransferRequest.setTransferAmount(400.0);
		try {
			// Stubbing-->Using stubbing we are telling to Mock object about what value will
			// be return at this method call.
			Mockito.doAnswer(new Answer<Void>() {

				public Void answer(InvocationOnMock invocation) throws Throwable {

					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());

			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");

		} catch (ApplicationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());

		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void testUserNameNull() throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(null);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("123456");
		accountTransferRequest.setReceiverAccount("423456");
		accountTransferRequest.setTransferAmount(400.0);
		try {
			// Stubbing-->Using stubbing we are telling to Mock object about what value will
			// be return at this method call.
			Mockito.doAnswer(new Answer<Void>() {

				public Void answer(InvocationOnMock invocation) throws Throwable {

					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");

		} catch (AuthenticationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());

		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * This scenario checks ,If i given empty user name then
	 * "Invalid_userName_and_password" exception will be arise.
	 * 
	 * @author Anjali
	 * @throws ApplicationException
	 * @throws BusinessException
	 * 
	 */
	@Test
	public void testUserNameEmpty() throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername("");
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("123456");
		accountTransferRequest.setReceiverAccount("423456");
		accountTransferRequest.setTransferAmount(200.0);
		try {
			// Stubbing-->Using stubbing we are telling to Mock object about what value will
			// be return at this method call.
			Mockito.doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) throws Throwable {
					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (AuthenticationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());
		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * testUserMisMatch is used to check UserName whether <br>
	 * if i am given wrong user name then it will throw
	 * "Invalid_userName_and_password" exception.
	 * 
	 * @author Anjali
	 * 
	 *         Data set:<br>
	 *         1.AccountTransferRequest.<br>
	 * 
	 *         Verification :<br>
	 *         1. Here i am checking if expected userName input and actual userName
	 *         output not same then it will throw exception .<br>
	 *         2. Verifying it's fail or not.<br>
	 */
	@Test
	public void testUserNameMisMatch() throws BusinessException, ApplicationException, AuthenticationException {
		String userName = "admin", password = VALID_PASSWORD;
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(userName);
		accountTransferRequest.setPassword(password);
		accountTransferRequest.setSenderAccount("093456");
		accountTransferRequest.setReceiverAccount("213456");
		accountTransferRequest.setTransferAmount(100.0);
		try {
			// Stubbing-->Using stubbing we are telling to Mock object about what value will
			// be return at this method call.
			Mockito.doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) throws Throwable {
					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (AuthenticationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());
		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * This scenario checks whether given password is null or not. If i given null
	 * password then it will throw an exception "Invalid_userName_and_password".
	 * 
	 * @author Anjali
	 * 
	 * @throws AuthenticationException
	 * 
	 *                                 Data set:<br>
	 *                                 1.AccountTransferRequest.<br>
	 * 
	 *                                 Verification :<br>
	 *                                 1.Here i am checking if expected password and
	 *                                 actual password not same then it will throw
	 *                                 exception .<br>
	 *                                 2.Verifying it's fail or not.<br>
	 */
	@Test
	public void testPasswordIsNull() throws BusinessException, ApplicationException, AuthenticationException {
		String password = null;
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(password);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(400.0);

		try {
			// Stubbing-->Using stubbing we are telling to Mock object about what value will
			// be return at this method call.
			Mockito.doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) throws Throwable {
					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exceptipon");
		} catch (AuthenticationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());

		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * This scenario checks whether given password is empty or not. If i given empty
	 * password then it will throw an exception "Invalid_userName_and_password".
	 * 
	 * @throws AuthenticationException
	 * 
	 * @author Anjali
	 * 
	 *         Data set:<br>
	 *         1.AccountTransferRequest.<br>
	 * 
	 *         Verification :<br>
	 *         1. Here i am checking if expected password input is empty and actual
	 *         password output not same then it will throw exception .<br>
	 *         2. Verifying it's fail or not.<br>
	 * 
	 * 
	 */
	@Test
	public void testPasswordIsEmpty() throws BusinessException, ApplicationException, AuthenticationException {
		String password = "";
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(password);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(400.0);

		try {
			// Stubbing-->Using stubbing we are telling to Mock object about what value will
			// be return at this method call.
			Mockito.doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) throws Throwable {
					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exceptipon");
		} catch (AuthenticationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());
		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * This scenario checks whether given password is wrong or not<br>
	 * . if password will mismatch then it will throw
	 * "Invalid_userName_and_password" exception<br>
	 * .
	 * 
	 * @author Anjali
	 * 
	 *         Data set:<br>
	 *         1.AccountTransferRequest.<br>
	 * 
	 * @throws AuthenticationException
	 * 
	 *                                 Verification :<br>
	 *                                 1. Here i am checking if expected password
	 *                                 input and actual password output not same
	 *                                 then it will throw exception .<br>
	 *                                 2. Verifying it's fail or not.<br>
	 */
	@Test
	public void testPasswordIsMisMatch() throws BusinessException, ApplicationException, AuthenticationException {
		String password = "jangir";
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(password);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(400.0);

		try {
			// Stubbing-->Using stubbing we are telling to Mock object about what value will
			// be return at this method call.
			Mockito.doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) throws Throwable {
					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (AuthenticationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());
		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void testBothUserNameAndPasswordNull()
			throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(null);
		accountTransferRequest.setPassword(null);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(400.0);

		try {
			// Stubbing-->Using stubbing we are telling to Mock object about what value will
			// be return at this method call.
			Mockito.doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) throws Throwable {
					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (AuthenticationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());
		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void testBothUserNameAndPasswordEmpty()
			throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername("");
		accountTransferRequest.setPassword("");
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(400.0);

		try {
			// Stubbing-->Using stubbing we are telling to Mock object about what value will
			// be return at this method call.
			Mockito.doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) throws Throwable {
					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (AuthenticationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());
		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void testBothUserNameAndPasswordMisMatch()
			throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername("hello");
		accountTransferRequest.setPassword("unit");
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(400.0);

		try {
			// Stubbing-->Using stubbing we are telling to Mock object about what value will
			// be return at this method call.
			Mockito.doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) throws Throwable {
					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (AuthenticationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());
		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * In this scenario checks if transfer amount is zero then
	 * "Transfer_Amount_InValid" exception will arise.
	 * 
	 * @author Anjali
	 * 
	 * 
	 * @throws BusinessException
	 * 
	 *                           Data set:<br>
	 *                           1.AccountTransferRequest.<br>
	 * 
	 *                           Verification :<br>
	 *                           1. Here i am giving transfer amount is zero as a
	 *                           input and i am checking actual amount output not
	 *                           same <br>
	 *                           then it will throw exception .<br>
	 *                           2. Verifying it's fail or not.<br>
	 * 
	 */
	@Test
	public void testTransferAmountIsZero() throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(0.0);
		try {

			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (BusinessException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Transfer_Amount_InValid", exception.getMessage());

		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void testSenderAccountNumberNull() throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(100.0);
		try {
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (ValidationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_Sender_AccountNumber", exception.getMessage());

		}

	}
	
	@Test
	public void testSenderAccountNumberEmpty() throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(100.0);
		try {
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (ValidationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_Sender_AccountNumber", exception.getMessage());

		}

	}
	
	@Test
	public void testSenderAccountNumberMisMatch() throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(100.0);
		try {
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (ValidationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_Sender_AccountNumber", exception.getMessage());

		}

	}
	
	@Test
	public void testReceiverAccountNumberNull() throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(100.0);
		try {
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (ValidationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_Receiver_AccountNumber", exception.getMessage());

		}

	}
	
	@Test
	public void testReceiverAccountNumberEmpty() throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(100.0);
		try {
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (ValidationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_Receiver_AccountNumber", exception.getMessage());

		}

	}
	
	@Test
	public void testReceiverAccountNumberMisMatch() throws BusinessException, ApplicationException, AuthenticationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(100.0);
		try {
			transferUnit.transfer(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (ValidationException exception) {
			// Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_Receiver_AccountNumber", exception.getMessage());

		}

	}
	
	
}
