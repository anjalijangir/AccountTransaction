package com.landq.account;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.landq.account.dao.IAccountDAO;
import com.landq.account.dao.IUserDAO;
import com.landq.account.domain.Account;
import com.landq.account.domain.User;
import com.landq.account.service.IAccountService;
import com.landq.account.service.IAuthenticationService;
import com.landq.account.service.impl.AccountService;
import com.landq.account.service.impl.AuthenticationService;

@Configuration
@Import(Config.class)
public class Run {

	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

		// save fake data to database to perform the operation
		generateFakeData(ctx);
		// Check for user login
		IAuthenticationService authenticationService = ctx.getBean(AuthenticationService.class);
		boolean isLoginSussess = authenticationService.doAuthentication("anjali", "admin");

		if (isLoginSussess) {
			System.out.println("Authentication successfull !!!");
			// Check Account Details for Sender and Receiver
			IAccountService accountService = ctx.getBean(AccountService.class);
			boolean isAccountValid = accountService.checkAccountDetails("123456", "9876543");
			// If Account is valid then print Account Validation successful and Transfer the
			// balance
			if (isAccountValid) {
				System.out.println("Account validation successful !!!");
				accountService.doTransfer("123456", "9876543", 20.0);
			}
		}

	}

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

		// create sender user account and save it to database
		Account senderAccount = new Account();
		senderAccount.setAccountNumber("123456");
		senderAccount.setBalance(200.00);
		senderAccount.setBankName("ICICI");
		senderAccount.setIfscCode("ICICI001");
		senderAccount.setUserName("anjali");
		accountRepository.save(senderAccount);

		// create a Receiver user and save it to database
		User receiver = new User();
		receiver.setEmail("abc@abc");
		receiver.setFirstName("Tom");
		receiver.setLastName("Cruiz");
		receiver.setUserName("tom");
		receiver.setPassword("tom");
		userRepository.save(receiver);

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
