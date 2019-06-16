package com.landq.account.service;
/**
 * AccountTransferRequest meant to if we want to transfer money from one account to another account
 * then what things we need which is:
 * 
 * 1.user name
 * 2.password
 * 3.sender account
 * 4.receiver account
 * 5.transfer amount
 * 
 * @author Anjali
 * 
 *
 */
public class AccountTransferRequest {

	private String username;
	private String password;
	private String senderAccount;
	private String receiverAccount;
	private Double transferAmount;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}

	public String getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(String receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public Double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(Double transferAmount) {
		this.transferAmount = transferAmount;
	}

}
