package com.landq.account.service;

public interface IAuthenticationService {
	public boolean doAuthentication(String userName, String password);
}
