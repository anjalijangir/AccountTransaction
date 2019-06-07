package com.landq.account.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.dao.IUserDAO;
import com.landq.account.domain.User;
import com.landq.account.service.IAuthenticationService;

@Component
public class AuthenticationService implements IAuthenticationService {

	@Autowired
	private IUserDAO userRepository;

	public boolean doAuthentication(String userName, String password) {
		User user = userRepository.findByUserName(userName);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return true;
			} else {
				System.out.println("Incorrect passsword");
				return false;
			}
		} else {
			System.out.println("User not found");
			return false;
		}
	}

}
