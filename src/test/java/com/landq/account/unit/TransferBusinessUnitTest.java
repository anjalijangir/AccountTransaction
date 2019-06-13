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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.ApplicationException;
import com.landq.account.dao.IAccountDAO;
import com.landq.account.dao.IUserDAO;
import com.landq.account.domain.Account;
import com.landq.account.exception.AuthenticationException;
import com.landq.account.exception.BusinessException;
import com.landq.account.service.AccountTransferRequest;

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

	@Test
	public void testTransferAmount() throws ApplicationException {

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
			Mockito.when(accountRepository.findByAccountNumber(Mockito.anyString())).thenReturn(senderAc).thenReturn(receiverAc);
			Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(senderAc).thenReturn(receiverAc);
			transferBusinessUnit.businessValidation(accountTransferRequest);	
		} catch (BusinessException exception) {
			Assert.fail("Exception_Not_Expected");
		}
		Mockito.verify(accountRepository, Mockito.times(2)).findByAccountNumber(Mockito.anyString());
		Mockito.verify(accountRepository, Mockito.times(2)).save(Mockito.any(Account.class));
	}
}
