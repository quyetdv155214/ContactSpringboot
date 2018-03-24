package com.websystique.springboot.service;

import org.springframework.stereotype.Repository;

import com.websystique.springboot.model.Account;

@Repository
public class AccountDAO {

	public Account login(Account account) {
		
		return new Account("aaa", "aaa");
	}

	public Account register(Account account) {
		
		//check valid acount
		// if ok create account 
		return new Account("aaa", "aaa");
	}

	public void logout(String id) {
		
		
	}

}
