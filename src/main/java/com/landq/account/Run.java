package com.landq.account;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.landq.account.domain.Account;
import com.landq.account.domain.User;
import com.landq.account.repository.AccountRepository;
import com.landq.account.repository.UserRepository;
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

			IAccountService accountService = ctx.getBean(AccountService.class);
			boolean isAccountValid = accountService.checkAccountDetails("123456", "9876543");
			if (isAccountValid) {
				System.out.println("Account validation successfull !!!");
				accountService.doTransfer("123456", "9876543", 20.0);
			}
		}

	}

	private static void generateFakeData(ApplicationContext ctx) {
		// get beans for database repository
		UserRepository userRepository = ctx.getBean(UserRepository.class);
		AccountRepository accountRepository = ctx.getBean(AccountRepository.class);

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

		// create a sender user and save it to database
		User receiver = new User();
		receiver.setEmail("abc@abc");
		receiver.setFirstName("Tom");
		receiver.setLastName("Cruiz");
		receiver.setUserName("tom");
		receiver.setPassword("tom");
		userRepository.save(receiver);

		// create sender user account and save it to database
		Account receiverAccount = new Account();
		receiverAccount.setAccountNumber("9876543");
		receiverAccount.setBalance(100.00);
		receiverAccount.setBankName("ICICI");
		receiverAccount.setIfscCode("ICICI001");
		receiverAccount.setUserName("tom");
		accountRepository.save(receiverAccount);

	}

}
