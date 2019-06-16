package com.landq.account.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.landq.account.domain.User;
/**
 * This IUserDAO will fetch the data from User database.
 */
@Repository
public interface IUserDAO extends CrudRepository<User, Integer> {

	// Query method
	// find user in USER table by email address
	User findByEmail(String email);

	// find user in USER table by userName
	User findByUserName(String userName);

}