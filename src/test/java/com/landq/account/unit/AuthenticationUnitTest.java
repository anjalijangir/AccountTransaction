package com.landq.account.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.landq.account.dao.IUserDAO;
import com.landq.account.domain.User;
import com.landq.account.exception.AuthenticationException;

public class AuthenticationUnitTest {
	@Mock
	private IUserDAO userRepository;

	@InjectMocks
	private TransferAuthenticationUnit authenticationUnit = new TransferAuthenticationUnit();
	
	private final String VALID_USERNAME = "anjali123";
	private final String VALID_PASSWORD = "admin";

	/**
	* This init functionality create the object for InjectMocks property.
	*/
	@Before
	public void init() {
	MockitoAnnotations.initMocks(this);
	}

	/**
	* In this scenario we are passing userName input is null,so it will throw the Exception
	* "Invalid_userName_and_password".<br>
	* 
	* Data set:<br>
	* 1.userName.<br>
	* 2.password.<br>
	* 
	* Verification :<br>
	* 1. Here we are checking expected and actual exception is same or not.<br>
	* 2. Verifying it's fail or not.<br>
	* 
	* @throws AuthenticationException
	*/
	@Test
	public void authenticateUserNameNull() {

	// data preparation
	String userName = null;
	String password = "anjali";

	try {
	// method call
	authenticationUnit.authenticate(userName, password);
	Assert.fail("Exception_is_Not_expected");
	} catch (AuthenticationException e) {
	Assert.assertEquals("Invalid_userName_and_password", e.getMessage());
	}

	// verifying
	Mockito.verify(userRepository, Mockito.times(0)).findByUserName(Mockito.anyString());
	}
	
	
	/**
	* Invalid Scenario for execute functionality.In this scenario we are passing
	* userName input is empty,so it will throw the Exception
	* "Invalid_userName_and_password".<br>
	* 
	* Data set:<br>
	* 1.userName.<br>
	* 2.password.<br>
	* 
	* Verification :<br>
	* 1. Here we are checking expected and actual exception is same or not.<br>
	* 2. Verifying it's fail or not.<br>
	* 
	* @throws AuthenticationException
	*/
	@Test
	public void authenticateUserNameEmpty() {

	// data preparation
	String userName = "";
	String password = "anjali";

	try {
	// method call
	authenticationUnit.authenticate(userName, password);
	Assert.fail("Exception_is_Not_expected");
	} catch (AuthenticationException e) {
	Assert.assertEquals("Invalid_userName_and_password", e.getMessage());
	}

	// verifying
	Mockito.verify(userRepository, Mockito.times(0)).findByUserName(Mockito.anyString());
	}
	
	/**
	* Invalid Scenario for execute functionality.In this scenario we are passing
	* userName input is null,so it will throw the Exception
	* "Invalid_userName_and_password".<br>
	* 
	* Data set:<br>
	* 1.userName.<br>
	* 2.password.<br>
	* 
	* Verification :<br>
	* 1. Here we are checking expected and actual exception is same or not.<br>
	* 2. Verifying it's fail or not.<br>
	* 
	* @throws AuthenticationException
	*/
	@Test
	public void authenticatePasswordNull() {

	// data preparation
	String userName = "anjali";
	String password = null;

	try {
	// method call
	authenticationUnit.authenticate(userName, password);
	Assert.fail("Exception_is_Not_expected");
	} catch (AuthenticationException e) {
	Assert.assertEquals("Invalid_userName_and_password", e.getMessage());
	}

	// verifying
	Mockito.verify(userRepository, Mockito.times(0)).findByUserName(Mockito.anyString());
	}
	
	/**
	* Invalid Scenario for execute functionality.In this scenario we are passing
	* password input is empty,so it will throw the Exception
	* "Invalid_userName_and_password".<br>
	* 
	* Data set:<br>
	* 1.userName.<br>
	* 2.password.<br>
	* 
	* Verification :<br>
	* 1. Here we are checking expected and actual exception is same or not.<br>
	* 2. Verifying it's fail or not.<br>
	* 
	* @throws AuthenticationException
	*/
	@Test
	public void authenticatePasswordEmpty() {

	// data preparation
	String userName = "anjali";
	String password = "";

	try {
	// method call
	authenticationUnit.authenticate(userName, password);
	Assert.fail(" exception_is_Not_expected");
	} catch (AuthenticationException e) {
	Assert.assertEquals("Invalid_userName_and_password", e.getMessage());
	}

	// verifying
	Mockito.verify(userRepository, Mockito.times(0)).findByUserName(Mockito.anyString());
	}
	
	/**
	* Invalid Scenario for execute functionality.In this scenario we are passing
	* userName input is mismatch,so it will throw the Exception
	* "Invalid_userName_and_password".<br>
	* 
	* Data set:<br>
	* 1.userName.<br>
	* 2.password.<br>
	* 
	* Verification :<br>
	* 1. Here we are checking expected and actual exception is same or not.<br>
	* 2. Verifying it's fail or not.<br>
	* 
	* @throws AuthenticationException
	*/
	@Test
	public void authenticateUserNameMismatch() {

	// data preparation
	String userName = "anjali";
	String password = VALID_PASSWORD;

	User user=new User();
	user.setFirstName("Anjali");
	user.setUserName("Jangir");
	
	try {
	Mockito.when(userRepository.findByUserName(userName)).thenReturn(null);
		
	// method call
	authenticationUnit.authenticate(userName, password);
	Assert.fail(" Exception_is_Not_expected");
	} catch (AuthenticationException e) {
	Assert.assertEquals("Invalid_userName_and_password", e.getMessage());
	}

	// verifying
	Mockito.verify(userRepository, Mockito.times(1)).findByUserName(Mockito.anyString());
	}
	
	/**
	* Invalid Scenario for execute functionality.In this scenario we are passing
	* password input is mismatch,so it will throw the Exception
	* "Invalid_userName_and_password".<br>
	* 
	* Data set:<br>
	* 1.userName.<br>
	* 2.password.<br>
	* 
	* Verification :<br>
	* 1. Here we are checking expected and actual exception is same or not.<br>
	* 2. Verifying it's fail or not.<br>
	* 
	* @throws AuthenticationException
	*/
	@Test
	public void authenticatePasswordMismatch() {

	// data preparation
	String userName = "anjali";
	String password = VALID_PASSWORD;

	User user=new User();
	user.setFirstName("Anjali");
	user.setUserName("Jangir");
	user.setPassword("1234");
	
	try {
	Mockito.when(userRepository.findByUserName(userName)).thenReturn(user);
		
	// method call
	authenticationUnit.authenticate(userName, password);
	Assert.fail(" exception_is_Not_expected");
	} catch (AuthenticationException e) {
	Assert.assertEquals("Invalid_userName_and_password", e.getMessage());
	}

	// verifying
	Mockito.verify(userRepository, Mockito.times(1)).findByUserName(Mockito.anyString());
	}
	
	/**
	* In this scenario we are passing userName and password for checking valid authentication.<br>
	* 
	* Data set:<br>
	* 1.userName.<br>
	* 2.password.<br>
	* 
	* Verification :<br>
	* 1. Here we are checking expected and actual exception is same or not.<br>
	* 2. Verifying it's fail or not.<br>
	* 
	* @throws AuthenticationException
	*/
	
	@Test
	public void authenticateValidUserNameAndPassword() {

	// data preparation
	String userName = VALID_USERNAME;
	String password = VALID_PASSWORD;

	User user=new User();
	user.setFirstName("Anjali");
	user.setUserName(VALID_USERNAME);
	user.setPassword(VALID_PASSWORD);
	
	try {
	Mockito.when(userRepository.findByUserName(userName)).thenReturn(user);
		
	// method call
	authenticationUnit.authenticate(userName, password);
	
	} catch (AuthenticationException e) {
		Assert.fail(" Exception_is_expected");
	}

	// verifying
	Mockito.verify(userRepository, Mockito.times(1)).findByUserName(Mockito.anyString());
	}

}
