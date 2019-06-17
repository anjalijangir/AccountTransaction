package com.landq.account;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.landq.account.dao.IAccountDAO;
import com.landq.account.dao.IUserDAO;
import com.landq.account.domain.Account;
import com.landq.account.domain.User;
import com.landq.account.service.AccountTransferRequest;
import com.landq.account.service.impl.AccountService;

/**
 * Run class contains main method for the application.
 * 
 * @author Anjali
 *
 */
@Configuration
@Import(Config.class)
public class Run {

	public static void main(String[] args) throws ApplicationException {

		// ApplicationContext is container which contains beans
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

		// save fake data to database to perform the operation
		generateFakeData(ctx);

		// Check for user login
		AccountService accountService = ctx.getBean(AccountService.class);

		// Creates the object for AccountTransferRequest and set the value and save to
		// data base.
		AccountTransferRequest accountTransferRequest = new AccountTransferRequest();
		accountTransferRequest.setUsername("anjali123");
		accountTransferRequest.setPassword("admin");
		accountTransferRequest.setSenderAccount("123456");
		accountTransferRequest.setReceiverAccount("9876543");
		accountTransferRequest.setTransferAmount(30.0);

		accountService.transferMoney(accountTransferRequest);

	}

	/**
	 * In generateFakeData we are passing inputs for user and account and save to
	 * database.
	 * 
	 * @param ctx
	 */
	private static void generateFakeData(ApplicationContext ctx) {
		// get beans for database DAO
		IUserDAO userRepository = ctx.getBean(IUserDAO.class);
		IAccountDAO accountRepository = ctx.getBean(IAccountDAO.class);

		// create a sender user and save it to database
		User sender = new User();
		sender.setEmail("xyz");
		sender.setFirstName("Anjali");
		sender.setLastName("Jangir");
		sender.setUserName("anjali");
		sender.setPassword("admin");
		userRepository.save(sender);

		// create a Receiver user and save it to database
		User receiver = new User();
		receiver.setEmail("abc@abc");
		receiver.setFirstName("Tom");
		receiver.setLastName("Cruiz");
		receiver.setUserName("tom");
		receiver.setPassword("tom");
		userRepository.save(receiver);

		// create sender user account and save it to database
		Account senderAccount = new Account();
		senderAccount.setAccountNumber("123456");
		senderAccount.setBalance(200.00);
		senderAccount.setBankName("ICICI");
		senderAccount.setIfscCode("ICICI001");
		senderAccount.setUserName("anjali");
		accountRepository.save(senderAccount);

		// create Receiver user account and save it to database
		Account receiverAccount = new Account();
		receiverAccount.setAccountNumber("9876543");
		receiverAccount.setBalance(100.00);
		receiverAccount.setBankName("ICICI");
		receiverAccount.setIfscCode("ICICI001");
		receiverAccount.setUserName("tom");
		accountRepository.save(receiverAccount);

	}

}
