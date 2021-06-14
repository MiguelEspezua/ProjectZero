package com.miguel.services;

import java.util.List;

import com.miguel.models.Account;
import com.miguel.models.MixUserAccount;
import com.miguel.models.User;

public interface AccountService {

	public boolean openAccount(User user);
	
	public List<Account> viewBalance(User user);
	
	public boolean viewCustomerAccount(String firstname, String lastname);
	
	public List<Account> viewAllUserAccounts(int userId);
	
	public List<MixUserAccount> viewPendingAccounts();
	
	public List<Account> viewAllAccounts(int userId); // for superuser
	
	public Account deposit(int accountId, double amount);
	
	public Account withdraw(int accountId, double amount);
	
	public boolean transfer(int origaccount, int destaccount, double amount);
	
	public boolean closeAccount(Account account);

	

}
