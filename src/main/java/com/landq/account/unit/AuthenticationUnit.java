package com.landq.account.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.ApplicationException;
import com.landq.account.dao.IUserDAO;
import com.landq.account.domain.User;
import com.landq.account.exception.AuthenticationException;
import com.landq.account.service.AccountTransferRequest;
/**
 * AuthenticationUnit class is checks whether user is authenticate or not for perform any transaction.
 * @author Anjali
 *
 */
@Component
public class AuthenticationUnit {

	@Autowired
	private IUserDAO userRepository;

	@Autowired
	AccountTransferRequest accountTransferRequest;
	/**
	 * Here doAuthentication method is used for verify the user to do transaction..using username and password
	 * If userName and password is not valid then user is not able to do transaction.
	 * 
	 * @param userName
	 * @param password
	 * @throws AuthenticationException
	 */
	public void doAuthentication(String userName, String password) throws AuthenticationException {
		User user = userRepository.findByUserName(userName);
		if (user != null) {
			if (!user.getPassword().equals(password)) {
				throw new AuthenticationException("Invalid_userName_and_password");
			} else {
				throw new AuthenticationException("Invalid_userName_and_password");
			}
		}
	}
}
