package com.miguel.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.miguel.models.Account;
import com.miguel.models.Transfer;
import com.miguel.models.User;
import com.miguel.utils.JDBCConnection;

public class TransferRepositoryImplementation implements TransferRepository {
	
	public static Connection conn = JDBCConnection.getConnection();
	public static UserRepository uRepo = new UserRepositoryImplementation();
	public static AccountRepository aRepo = new AccountRepositoryImplementation();
	public static TransferRepository tRepo = new TransferRepositoryImplementation();
	
	

	@Override
	public boolean createTransfer(Transfer transfer, Account account) {
		
		try {
			String sql = "insert into transactions values (default, default, ?,?,?,?,?) returning *;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getAccountNumber());
			ps.setString(2, transfer.getTransferType());
			ps.setDouble(3, transfer.getTransferAmount());
			ps.setDouble(4, transfer.getAccountBalance());
			ps.setInt(5, account.getUserId());
				
				boolean success = ps.execute();
				
				return success;
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return false;
		}


	@Override
	public Transfer getTransfer(int transferId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> getAllTransfers() {
		
		List<Transfer> transactions = new ArrayList<Transfer>();
		
		try {
		
			String sql = "select * from transactions;"; // all rows
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//ps.setInt(1, user.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Transfer trans = new Transfer();
				trans.setTransferId(rs.getInt("transid"));
				trans.setDatetime(rs.getDate("transdate"));
				trans.setAccountNumber(rs.getInt("accountnumber"));
				trans.setTransferAmount(rs.getDouble("transamount"));
				trans.setAccountBalance(rs.getDouble("accountbalance"));
				trans.setUserId(rs.getInt("userid"));
				
				transactions.add(trans);
				
			}

			return transactions;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public List<Transfer> getAllUserTransfers(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> getAllBankAccountTransfers(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTransfer(Transfer transfer) {
		// TODO Auto-generated method stub
		return false;
	}

}
