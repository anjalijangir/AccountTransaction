package com.landq.account.unit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.landq.account.exception.ValidationException;
import com.landq.account.service.AccountTransferRequest;

/**
 * This class used for perform Mandatory Validation.Here i am checking :
 * 
 * 1.checkCredentials--Checks user name and password if null or empty then throw
 * exception.
 * 
 * 2.validateTransferAmount--Checks Transfer amount is zero,null and negative
 * then throw exception.
 * 
 * 3.checkAccountValidation--Checks Account number if null or empty then then
 * throw exception.
 * 
 * @author Anjali
 *
 */
public class TransferValidationUnit {

	public static final int ACCOUNT_NUBER_LENGTH = 6;
	public static final String ACCOUNT_NUMBER_PATTERN = "^[0-9]*$";

	public void validate(AccountTransferRequest accountTransferRequest) throws ValidationException {

		String userName = accountTransferRequest.getUsername();
		String password = accountTransferRequest.getPassword();
		String senderAccountNumber = accountTransferRequest.getSenderAccount();
		String receiverAccountNumber = accountTransferRequest.getReceiverAccount();
		Double transferAmount = accountTransferRequest.getTransferAmount();

		// Checks credential information
		checkCredentials(userName, password);

		// Checks transfer validation
		validateTransferAmount(transferAmount);

		// Checks Account Validation
		checkAccountValidation(senderAccountNumber, receiverAccountNumber);
	}

	/**
	 * Checks Credentials information like user name and password if null or empty
	 * then throw exception
	 * 
	 * @param userName
	 * @param password
	 * @throws ValidationException-- When the input validation fails
	 */
	private void checkCredentials(String userName, String password) throws ValidationException {

		// Checks userName if null or empty then throw ValidationException
		if (userName == null || userName.isEmpty()) {
			throw new ValidationException("UserName_Cannot_Be_Null_Or_Empty");
		}

		// Checks password if null or empty then throw ValidationException
		if (password == null || password.isEmpty()) {
			throw new ValidationException("Password_Cannot_Be_Null_Or_Empty");
		}
	}

	/**
	 * Checks Transfer amount is zero,null and negative then throw exception.
	 * 
	 * @param userName
	 * @param password
	 * @throws ValidationException-- When the input validation fails
	 */

	private void validateTransferAmount(Double transferAmount) throws ValidationException {
		if (transferAmount == null) {
			throw new ValidationException("transferAmount_Cannot_Be_Null");
		}

		if (transferAmount <= 0) {
			throw new ValidationException("transferAmount_Cannot_Be_Negative_Or_Zero");
		}

	}

	/**
	 * Checks Valid pattern of any Account holder's account number. this method
	 * widely used to define constraint on strings such as password.
	 */

	private boolean isAccountNumberPatternValid(String accountNumber) {
		Pattern pattern = Pattern.compile(ACCOUNT_NUMBER_PATTERN);
		Matcher matcher = pattern.matcher(accountNumber);
		return matcher.matches();
	}

	/**
	 * checkAccountValidation method used to validate the Account holder's Account
	 * Number .
	 * 
	 * Checks Account number if null or empty then then throw exception.
	 * 
	 * Checks Account Number if not in valid pattern then throw exception.
	 * 
	 * Checks Account Number if not in validate length then throw exception.
	 * 
	 * @param senderAcccountNumber
	 * @param receiverAcccountNumber
	 * @throws ValidationException-- When the input validation fails.
	 */
	private void checkAccountValidation(String senderAcccountNumber, String receiverAcccountNumber)
			throws ValidationException {

		// Checks sender account if null or empty then throw ValidationException
		if (senderAcccountNumber == null || senderAcccountNumber.isEmpty()) {
			throw new ValidationException("Sender_Account_Cannot_Be_Null_Or_Empty");
		}

		// Checks Receiver account if null or empty then throw ValidationException
		if (receiverAcccountNumber == null || receiverAcccountNumber.isEmpty()) {
			throw new ValidationException("Receiver_Account_Cannot_Be_Null_Or_Empty");
		}

		// Checks Account holder's account number size must be valid length.
		if (senderAcccountNumber.length() != ACCOUNT_NUBER_LENGTH
				|| receiverAcccountNumber.length() != ACCOUNT_NUBER_LENGTH) {
			throw new ValidationException("Invalid_Sender_And_Receiver_AccountNumber");
		}

		// Checks Account holder"s account number must be in "[0-9]" pattern.
		if (!isAccountNumberPatternValid(senderAcccountNumber)
				|| !isAccountNumberPatternValid(receiverAcccountNumber)) {
			throw new ValidationException("Invalid_Sender_And_Receiver_AccountNumber");
		}

		// Checks if Sender and Receiver account same then throw
		// Invalid_Sender_And_Receiver_AccountNumber exception
		if (senderAcccountNumber.equals(receiverAcccountNumber)) {
			throw new ValidationException("Invalid_Sender_And_Receiver_AccountNumber");
		}

	}
}
