package com.miguel.services;

import java.util.List;

import com.miguel.models.Account;
import com.miguel.models.Transfer;
import com.miguel.models.User;
import com.miguel.repositories.TransferRepository;
import com.miguel.repositories.TransferRepositoryImplementation;

public class TransferServiceImplementation implements TransferService {
	
	TransferRepository tRepo = new TransferRepositoryImplementation();

	@Override
	public List<Transfer> viewAllTransfers() {
		List<Transfer> transfers = tRepo.getAllTransfers();
		System.out.println("=============================");
		System.out.println("   log of all transactions   ");
		System.out.println("=============================\n");
		System.out.println("ID UserID Acct Oper Amount Balanc Date");
		for (Transfer trans : transfers) {
			System.out.println(trans.getTransferId() +"  "+trans.getUserId() + "  " + trans.getAccountNumber()+
					"  "+ trans.getTransferType()+"  "+trans.getTransferAmount()+"   "+trans.getAccountBalance()+
					"   "+ trans.getDatetime());
			
		}
		return transfers;
	}

	@Override
	public List<Transfer> viewAllUserTransfers(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> viewAllBankAccountTransfers(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

}
