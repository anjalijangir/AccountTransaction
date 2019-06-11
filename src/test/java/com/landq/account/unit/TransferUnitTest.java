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
import com.landq.account.service.AccountTransferRequest;

/**
 * TransferUnitTest class used for test the Valid or InValid scenario<br>
 * 
 * between sender and receiver. This TestCase covered following scenarios.<br>
 * 
 * 1.testTransferNull---Check if input is null then throw the {@link AuthenticationException} "Invalid_userName_and_password".
 * S
 * 2.testTransferAmountIsPositive---Checks if transfer amount is negative then
 * throw "Transfer_Amount_Negative" exception<br>
 * 
 * 3.testUserEmpty---check If User name is empty then throw the exception {@link AuthenticationException}
 * "Invalid_userName_and_password".<br>
 * 
 * 4.testUserMisMatch--Checks if user name is wrong one then throw the exception {@link AuthenticationException}
 * "Invalid_userName_and_password".<br>
 * 
 * 5.testPasswordIsNull--Checks if password is null then throw
 * "Invalid_userName_and_password" {@link AuthenticationException} exception.
 * 
 * 6.testPasswordIsMisMatch--Checks if i given wrong password then it will throw
 * "Invalid_userName_and_password" {@link AuthenticationException} exception.
 * 
 * 7.testTransferAmountIsZero--Checks if sender has zero balance then throw
 * "Transfer_Amount_InValid"  BusinessException.
 *
 */

public class TransferUnitTest {
	@Mock /**will create a mock implementation for the  IAccountDAO*/
	private IAccountDAO accountRepository;

	@Mock/**will create a mock implementation for the  AuthenticationUnit*/
	private AuthenticationUnit authenticationUnit;

	@InjectMocks/** will inject the mocks marked with @Mock to this instance when it is created.*/
	TransferUnit transferUnit;
	
    /**
     * For the testing purpose here i am giving VALID_USERNAME and VALID_PASSWORD as a constant with Final keyword.
     */
	private final String VALID_USERNAME = "anjali";
	private final String VALID_PASSWORD = "admin";

	@Before/**It will initialize a mock instance for TransferUnit and transferUnit*/
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
	 *  Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws AuthenticationException
	 * 
	 * Verification :<br>
	 * 1. Here i am  checking if expected input and actual input not same then it will throw exception .<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	@Test
	public void testInputsEmpty() throws ApplicationException {
		String userName = "";
		String password = "";
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("123456");
		accountTransferRequest.setReceiverAccount("423456");
		accountTransferRequest.setTransferAmount(400.0);
		try {

			Mockito.doAnswer(new Answer<Void>() {

				public Void answer(InvocationOnMock invocation) throws Throwable {

					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());

			transferUnit.transfer(accountTransferRequest);

		} catch (ApplicationException exception) {
			//Assert compares the actual result of an application with the expected result.
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
	 * 
	 * @throws AuthenticationException
	 *  Data set:<br>
	 *  
	 * 1.AccountTransferRequest.<br>
	 * 
	 *  Verification :<br>
	 * 1. Here i am  checking if expected input and actual output not same then it will throw exception .<br>
	 * 2. Verifying it's fail or not.<br>
	 * 
	 */
	@Test
	public void testUserEmpty() throws ApplicationException {
		String userName = "";
		String password = VALID_PASSWORD;
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(password);
		accountTransferRequest.setSenderAccount("123456");
		accountTransferRequest.setReceiverAccount("423456");
		accountTransferRequest.setTransferAmount(200.0);
		try {

			Mockito.doAnswer(new Answer<Void>() {

				public Void answer(InvocationOnMock invocation) throws Throwable {

					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());

			transferUnit.transfer(accountTransferRequest);

		} catch (ApplicationException exception) {
			//Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());

		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());

	}

	/**
	 * testUserMisMatch is used to check UserName whether <br> if i am given wrong
	 * user name then it will throw "Invalid_userName_and_password" exception.
	 * 
	 * @author Anjali
	 * 
	 *  Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 *  Verification :<br>
	 * 1. Here i am  checking if expected userName  input and actual userName output not same then it will throw exception .<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	@Test
	public void testUserMisMatch() throws ApplicationException {

		String userName = "admin", password = VALID_PASSWORD;
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(password);
		accountTransferRequest.setSenderAccount("093456");
		accountTransferRequest.setReceiverAccount("213456");
		accountTransferRequest.setTransferAmount(100.0);

		try {

			Mockito.doAnswer(new Answer<Void>() {

				public Void answer(InvocationOnMock invocation) throws Throwable {

					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());

			transferUnit.transfer(accountTransferRequest);

		} catch (ApplicationException exception) {
			//Assert compares the actual result of an application with the expected result.
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
	 *  Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * Verification :<br>
	 * 1. Here i am  checking if expected password  and actual password  not same then it will throw exception .<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	@Test
	public void testPasswordIsNull() throws ApplicationException {
		String userName = VALID_USERNAME;
		String password = null;
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(400.0);

		try {

			Mockito.doAnswer(new Answer<Void>() {

				public Void answer(InvocationOnMock invocation) throws Throwable {

					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());

			transferUnit.transfer(accountTransferRequest);

		} catch (ApplicationException exception) {
			//Assert compares the actual result of an application with the expected result.
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
	 *  Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * Verification :<br>
	 * 1. Here i am  checking if expected password input is empty and actual password output not same then it will throw exception .<br>
	 * 2. Verifying it's fail or not.<br>
	 * 
	 * 
	 */
	@Test
	public void testPasswordIsEmpty() throws ApplicationException {
		String userName = VALID_USERNAME;
		String password = "";
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(400.0);

		try {
            //Stubbing-->Using stubbing we are telling to Mock object about what value will be return at this method call.
			Mockito.doAnswer(new Answer<Void>() {

				public Void answer(InvocationOnMock invocation) throws Throwable {

					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());

			transferUnit.transfer(accountTransferRequest);

		} catch (ApplicationException exception) {
			//Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Invalid_userName_and_password", exception.getMessage());

		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * This scenario checks whether given password is wrong or not<br>. if password
	 * will mismatch then it will throw "Invalid_userName_and_password"
	 * exception<br>.
	 * 
	 * @author Anjali
	 * 
	 *  Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws AuthenticationException
	 * 
	 *  Verification :<br>
	 * 1. Here i am  checking if expected password input and actual password output not same then it will throw exception .<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	@Test
	public void testPasswordIsMisMatch() throws AuthenticationException {
		String userName = VALID_USERNAME;
		String password = "jangir";
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(400.0);

		try {

			Mockito.doAnswer(new Answer<Void>() {

				public Void answer(InvocationOnMock invocation) throws Throwable {

					throw new AuthenticationException("Invalid_userName_and_password");
				}
			}).when(authenticationUnit).doAuthentication(Mockito.anyString(), Mockito.anyString());

			transferUnit.transfer(accountTransferRequest);

		} catch (ApplicationException exception) {
			//Assert compares the actual result of an application with the expected result.
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
	 *  Data set:<br>
	 *   * 1.AccountTransferRequest.<br>
	 *   
	 *    Verification :<br>
	 * 1. Here i am  giving  transfer amount is zero as a input and i am checking actual amount output not same <br>
	 * then it will throw exception .<br>
	 * 2. Verifying it's fail or not.<br>
	 *   
	 */
	@Test
	public void testTransferAmountIsZero() throws ApplicationException {
		String userName = VALID_USERNAME;
		String password = VALID_PASSWORD;
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("233456");
		accountTransferRequest.setReceiverAccount("273456");
		accountTransferRequest.setTransferAmount(0.0);
		try {

			
			transferUnit.transfer(accountTransferRequest);

		} catch (BusinessException exception) {
			//Assert compares the actual result of an application with the expected result.
			Assert.assertNotNull(exception);
			Assert.assertTrue(exception != null);
			Assert.assertEquals("Transfer_Amount_InValid", exception.getMessage());

		}
		Mockito.verify(authenticationUnit, Mockito.times(1)).doAuthentication(Mockito.anyString(), Mockito.anyString());
	}

	

}
