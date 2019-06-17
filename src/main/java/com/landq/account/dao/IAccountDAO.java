package com.landq.account.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.landq.account.domain.Account;

/**
 * This IAccountDAO will fetch the data from Account database for perform the
 * Crud operation.
 */
@Repository
public interface IAccountDAO extends CrudRepository<Account, Integer> {

	// Query method
	// find user account in Account table by userName
	Account findByUserName(String userName);

	// find user account in Account table by accountNumber
	Account findByAccountNumber(String accountNumber);

}