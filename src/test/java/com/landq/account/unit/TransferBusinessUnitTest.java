package com.landq.account.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.landq.account.ApplicationException;
import com.landq.account.dao.IAccountDAO;
import com.landq.account.domain.Account;
import com.landq.account.exception.AuthenticationException;
import com.landq.account.exception.BusinessException;
import com.landq.account.service.AccountTransferRequest;
/**
 * This scenario checks business validation meant to if amount is zero and null then throw "Transfer_Amount_InValid".
 * If amount is inSufficient then throw "Insufficient_Balance" exception.
 * 
 * In this scenario I am checking following Test case:-
 * testTransferAmountSuccess--Checks transfer amount valid or not.
 * testTransferAmountZero--Checks transfer amount zero or not.
 * testTransferAmountInSufficeint--Checks transfer amount sufficient or not.
 * @author Anjali
 *
 */
public class TransferBusinessUnitTest {

	@Mock
	private IAccountDAO accountRepository;

	@InjectMocks
	TransferBusinessUnit transferBusinessUnit = new TransferBusinessUnit();

	/**
	 * This init functionality create the object for InjectMocks property.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private final String VALID_USERNAME = "anjali123";
	private final String VALID_PASSWORD = "admin";
	private final String VALID_SENDER_ACCOUNT_NUMBER = "123456";
	private final String VALID_RECEIVER_ACCOUNT_NUMBER = "987654";
	
	/**
	 * This scenario checks whether transfer amount valid or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * 
	 * @throws BusinessException
	 * 
	 */ 
	 
	@Test
	public void testTransferAmountSuccess() throws ApplicationException {

		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount("VALID_RECEIVER_ACCOUNT_NUMBER");
		accountTransferRequest.setTransferAmount(100.0);

		Account senderAc = new Account();
		senderAc.setAccountNumber(VALID_SENDER_ACCOUNT_NUMBER);
		senderAc.setBalance(200.0);
		senderAc.setUserName("Anjali");
		senderAc.setBankName("SBI");

		Account receiverAc = new Account();
		receiverAc.setAccountNumber(VALID_RECEIVER_ACCOUNT_NUMBER);
		receiverAc.setBalance(100.0);
		receiverAc.setUserName("jangir");
		receiverAc.setBankName("SBI");

		try {
			Mockito.when(accountRepository.findByAccountNumber(Mockito.anyString())).thenReturn(senderAc)
					.thenReturn(receiverAc);
			Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(senderAc)
					.thenReturn(receiverAc);
			transferBusinessUnit.businessValidation(accountTransferRequest);
		} catch (BusinessException exception) {
			Assert.fail("Exception_Not_Expected");
		}
		Mockito.verify(accountRepository, Mockito.times(2)).findByAccountNumber(Mockito.anyString());
		Mockito.verify(accountRepository, Mockito.times(2)).save(Mockito.any(Account.class));
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
	 * @throws BusinessException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected transfer amount  input and actual output same or not then 
	 * it will throw exception "Transfer_Amount_InValid".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	@Test
	public void testTransferAmountZero() throws ApplicationException {

		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount("VALID_RECEIVER_ACCOUNT_NUMBER");
		accountTransferRequest.setTransferAmount(0.0);

		Account senderAc = new Account();
		senderAc.setAccountNumber(VALID_SENDER_ACCOUNT_NUMBER);
		senderAc.setBalance(200.0);
		senderAc.setUserName("Anjali");
		senderAc.setBankName("SBI");

		Account receiverAc = new Account();
		receiverAc.setAccountNumber(VALID_RECEIVER_ACCOUNT_NUMBER);
		receiverAc.setBalance(100.0);
		receiverAc.setUserName("jangir");
		receiverAc.setBankName("SBI");

		try {
			Mockito.when(accountRepository.findByAccountNumber(Mockito.anyString())).thenReturn(senderAc)
					.thenReturn(receiverAc);
			transferBusinessUnit.businessValidation(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (BusinessException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Transfer_Amount_InValid", exception.getMessage());

		}
		Mockito.verify(accountRepository, Mockito.times(2)).findByAccountNumber(Mockito.anyString());
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
	 * @throws BusinessException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected transfer amount  input and actual output same or not then 
	 * it will throw exception"transferAmount_Cannot_Be_Negative_Or_Zero".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	@Test
	public void testTransferAmountNegative() throws BusinessException, AuthenticationException, ApplicationException {
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount("123456");
		accountTransferRequest.setReceiverAccount("123456");
		accountTransferRequest.setTransferAmount(-100.0);
		

		Account senderAc = new Account();
		senderAc.setAccountNumber(VALID_SENDER_ACCOUNT_NUMBER);
		senderAc.setBalance(200.0);
		senderAc.setUserName("Anjali");
		senderAc.setBankName("SBI");

		Account receiverAc = new Account();
		receiverAc.setAccountNumber(VALID_RECEIVER_ACCOUNT_NUMBER);
		receiverAc.setBalance(100.0);
		receiverAc.setUserName("jangir");
		receiverAc.setBankName("SBI");
		

		try {
			Mockito.when(accountRepository.findByAccountNumber(Mockito.anyString())).thenReturn(senderAc)
					.thenReturn(receiverAc);
			transferBusinessUnit.businessValidation(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (BusinessException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Transfer_Amount_InValid", exception.getMessage());

		}
		Mockito.verify(accountRepository, Mockito.times(2)).findByAccountNumber(Mockito.anyString());
	}
	
	



	
	/**
	 * This scenario checks whether transfer amount sufficient or not.
	 *
	 * 
	 * @author Anjali
	 * 
	 * Data set:<br>
	 * 1.AccountTransferRequest.<br>
	 * 
	 * @throws BusinessException
	 * 
	 * Verification :<br>
	 * 1. Here i am checking if expected transfer amount  input and actual output same or not then 
	 * it will throw exception "Insufficient_Balance".<br>
	 * 2. Verifying it's fail or not.<br>
	 */
	@Test
	public void testTransferAmountInSufficeint() throws ApplicationException {

		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername(VALID_USERNAME);
		accountTransferRequest.setPassword(VALID_PASSWORD);
		accountTransferRequest.setSenderAccount(VALID_SENDER_ACCOUNT_NUMBER);
		accountTransferRequest.setReceiverAccount("VALID_RECEIVER_ACCOUNT_NUMBER");
		accountTransferRequest.setTransferAmount(201.0);

		Account senderAc = new Account();
		senderAc.setAccountNumber(VALID_SENDER_ACCOUNT_NUMBER);
		senderAc.setBalance(200.0);
		senderAc.setUserName("Anjali");
		senderAc.setBankName("SBI");

		Account receiverAc = new Account();
		receiverAc.setAccountNumber(VALID_RECEIVER_ACCOUNT_NUMBER);
		receiverAc.setBalance(100.0);
		receiverAc.setUserName("jangir");
		receiverAc.setBankName("SBI");

		try {
			Mockito.when(accountRepository.findByAccountNumber(Mockito.anyString())).thenReturn(senderAc)
					.thenReturn(receiverAc);
			transferBusinessUnit.businessValidation(accountTransferRequest);
			Assert.fail("No_Exception");
		} catch (BusinessException exception) {
			Assert.assertNotNull(exception);
			Assert.assertEquals("Insufficient_Balance", exception.getMessage());

		}
		Mockito.verify(accountRepository, Mockito.times(2)).findByAccountNumber(Mockito.anyString());
	}
	
	
}
