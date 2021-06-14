package com.miguel.services;

import java.util.List;

import com.miguel.models.Account;
import com.miguel.models.Transfer;
import com.miguel.models.User;

public interface TransferService {
	
	public List<Transfer> viewAllTransfers();
	
	public List<Transfer> viewAllUserTransfers(User user);
	
	public List<Transfer> viewAllBankAccountTransfers(Account account);

}
