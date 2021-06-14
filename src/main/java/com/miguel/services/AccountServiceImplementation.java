package com.miguel.services;

//import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.miguel.models.Account;
import com.miguel.models.MixUserAccount;
import com.miguel.models.Transfer;
import com.miguel.models.User;
import com.miguel.repositories.AccountRepository;
import com.miguel.repositories.AccountRepositoryImplementation;
import com.miguel.repositories.TransferRepository;
import com.miguel.repositories.TransferRepositoryImplementation;
import com.miguel.repositories.UserRepository;
import com.miguel.repositories.UserRepositoryImplementation;

public class AccountServiceImplementation implements AccountService {
	
	public AccountRepository aRepo = new AccountRepositoryImplementation();
	public UserRepository userRepo = new UserRepositoryImplementation();
	public TransferRepository tRepo = new TransferRepositoryImplementation();

	@Override
	public boolean openAccount(User user) {
		boolean success = aRepo.openAccount(user);
		if (success) {
			System.out.println("New account is open, approval pending");
		} else {
			System.out.println("Apply for account is failure");
		}
		return success;
	}

	@Override
	public List<Account> viewBalance(User user) {
		List<Account> userAccounts = aRepo.getBalance(user);
		System.out.println("=============================");
		System.out.println("******* YOUR ACCOUNTs *******");
		System.out.println("=============================");
		System.out.println("ACCOUNT NUMBER..BALANCE..Status ");
		for (Account acct : userAccounts) {
			System.out.println(acct.getAccountNumber() + ".." + acct.getBalance()+".."+acct.getStatus());
			
		}
		return userAccounts;
	}

	
	@Override
	public List<Account> viewAllUserAccounts(int userId) {
        return null;
	}
	@Override
	public List<MixUserAccount> viewPendingAccounts() {
		Scanner scan = new Scanner(System.in);
		
		//boolean run = true;
		//while(run) {
		List<MixUserAccount> pendingaccount = aRepo.getPendingAccounts();
		if (pendingaccount != null) {
			
		System.out.println("\tACCOUNT PENDING TO ACTIVATE");
		System.out.println("Acc Num..First..Last..Status");
		for (MixUserAccount mua : pendingaccount) {
			System.out.println(mua.getAccountNumber() + ".." + mua.getFirst() +".."+
		           mua.getLast() +".."+mua.getAccountstatus());
			
		}
		System.out.println("Input Account Number to be approve :");
		int accoutnum = scan.nextInt();
		
		boolean approveaccount = aRepo.upDateAccountStatus(accoutnum);
		//System.out.println(approveaccount);
		if (approveaccount) { 
			System.out.println("account number :"+ accoutnum + " Is approved!");
		} else { 
			System.out.println("The account number :"+ accoutnum + " No exist!");
		}
		
	} else {
		System.out.println("NoT Pending account for approve.");
	}   
	 
		//System.out.println("Do you like continue .. [y/n]");
		/*String response = scan.nextLine();
		if ("y".equalsIgnoreCase(response)) {
			run = true;

		
		} else {
			run = false;
			
		}
		*/
		//}
		//scan.close();
		return null;
	}

	@Override
	public List<Account> viewAllAccounts(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account withdraw(int accountId, double amount) {
		Account useraccount = aRepo.getAccountByAccountNumber(accountId);
		if(useraccount.getBalance() >= amount) {
		useraccount.setBalance(useraccount.getBalance() - amount);
		aRepo.upDateAccount(useraccount);
		Transfer t = new Transfer(useraccount.getAccountNumber(), "withdrawal", amount,useraccount.getBalance());
		tRepo.createTransfer(t, useraccount);
		return useraccount;
		} else {
			System.out.println("there are not enough funds!");
		}
		return null;
	}
	
	@Override
	public Account deposit(int accountId, double amount) {
		Account useraccount = aRepo.getAccountByAccountNumber(accountId);
		if(useraccount.getStatus().equals("Active")){
		System.out.println("before depostit balance "+ useraccount.getBalance());
		useraccount.setBalance(useraccount.getBalance() + amount);
		System.out.println("before depostit balance "+ useraccount.getBalance());
		aRepo.upDateAccount(useraccount); //update balance
		Transfer t = new Transfer(useraccount.getAccountNumber(), "deposit", amount,useraccount.getBalance());
		tRepo.createTransfer(t, useraccount);
		return useraccount;
		}else {
			System.out.println("this account is not Active!");
		}
		return null;
	}


	@Override
	public boolean transfer(int origaccount, int destaccount, double amount) {
		Account origenaccount = aRepo.getAccountByAccountNumber(origaccount);
		Account destinaccount = aRepo.getAccountByAccountNumber(destaccount);
		if((origenaccount.getStatus().equals("Active")&&(destinaccount.getStatus().equals("Active")))) {
		if(origenaccount.getBalance() >= amount) {
			origenaccount.setBalance(origenaccount.getBalance() - amount);
			aRepo.upDateAccount(origenaccount);
			Transfer to = new Transfer(origenaccount.getAccountNumber(), "withdrawal", amount,origenaccount.getBalance());
			tRepo.createTransfer(to, origenaccount);
			destinaccount.setBalance(destinaccount.getBalance() + amount);
			aRepo.upDateAccount(destinaccount);
			Transfer td = new Transfer(destinaccount.getAccountNumber(), "deposit", amount,destinaccount.getBalance());
			tRepo.createTransfer(td, destinaccount);
			
			return true;
		} else {
		return false;
		}
		}else {
			System.out.println("Account is not active!");
			return false;
		}
		
	}
	
	@Override
	public boolean viewCustomerAccount(String firstname, String lastname) {
		
		User user = new User();
		user = userRepo.getUserbyname(firstname, lastname);
	
		List<Account> userAccounts = aRepo.getBalance(user);
		System.out.println("=============================");
		System.out.println("******* YOUR ACCOUNTs *******");
		System.out.println("=============================");
		System.out.println("ACCOUNT NUMBER ....... BALANCE");
		for (Account acct : userAccounts) {
			System.out.println(acct.getAccountNumber() + "........." + acct.getBalance());
			 
		// TODO Auto-generated method stub
		
	}return false;
	}
	
	@Override
	public boolean closeAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

}
