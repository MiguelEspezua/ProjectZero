package com.miguel.repositories;

import java.util.List;

import com.miguel.models.Account;
import com.miguel.models.Transfer;
import com.miguel.models.User;

public interface TransferRepository {
	// CREATE
	public boolean createTransfer(Transfer transfer, Account account) ;
	
	// READ
	public Transfer getTransfer(int transferId) ;
	
	public List<Transfer> getAllTransfers() ;
	
	public List<Transfer> getAllUserTransfers(User user) ;
	
	public List<Transfer> getAllBankAccountTransfers(Account account) ;
	
	public boolean updateTransfer(Transfer transfer) ;

}
