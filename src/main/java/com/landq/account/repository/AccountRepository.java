package com.landq.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.landq.account.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

	//Query method
	// find user account in Account table by userName
	Account findByUserName(String userName);
	
	// find user account in Account table by accountNumber
	Account findByAccountNumber(String accountNumber);

}