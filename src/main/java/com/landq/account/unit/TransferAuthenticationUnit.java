package com.landq.account.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.landq.account.dao.IUserDAO;
import com.landq.account.domain.User;
import com.landq.account.exception.AuthenticationException;

/**
 * AuthenticationUnit class is checks whether user is authenticate or not for
 * perform any transaction.
 * 
 * @author Anjali
 *
 */
@Component
public class TransferAuthenticationUnit {

	@Autowired
	private IUserDAO userDao;

	/**
	 * Here authentication method is used for authenticates the user to do
	 * transaction..using username and password If userName and password is not
	 * valid then user is not able to do transaction.
	 * 
	 * It will test first if userName or password empty or null then throw the @AuthenticationException
	 * 
	 * @param userName
	 * @param password
	 * @throws AuthenticationException
	 */
	public void authenticate(String userName, String password) throws AuthenticationException {
		if ((userName == null || userName.isEmpty()) || (password == null || password.isEmpty())) {
			throw new AuthenticationException("Invalid_userName_and_password");
		}
		User user = userDao.findByUserName(userName);
		if (user != null) {
			if (user.getPassword() == null || !user.getPassword().equals(password)) {
				throw new AuthenticationException("Invalid_userName_and_password");
			} else {
				// successfully authenticated.
			}
		} else {
			throw new AuthenticationException("Invalid_userName_and_password");
		}
	}
}
