package com.miguel.repositories;

import java.util.List;

import com.miguel.models.Account;
import com.miguel.models.MixUserAccount;
import com.miguel.models.User;

public interface AccountRepository {
	
	// CREATE
	public boolean openAccount(User user) ; // do I want these to return a User? 
	
	// READ
	
	public List<MixUserAccount> getPendingAccounts();
	
	public List<Account> getBalance(User user);
	
	public Account getAccountByAccountNumber(int accountNumber) ;
	
	public List<Account> getAccountByUser(User user);
	
	public List<Account> getAllUserAccountsById(int userId) ;
	
	public List<Account> getAllAccounts() ;
	
	// UPDATE
	
	public boolean upDateAccountStatus(int account);
	
	public boolean upDateAccount(Account account);
	
	// DELETE
	public boolean closeAccount(Account account);

	

}
