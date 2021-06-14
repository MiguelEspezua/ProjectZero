package com.miguel.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.miguel.models.Account;
import com.miguel.models.MixUserAccount;
import com.miguel.models.User;
import com.miguel.utils.JDBCConnection;

public class AccountRepositoryImplementation implements AccountRepository {
	
	// Establish a connection to database
	public static Connection conn = JDBCConnection.getConnection();
	public static UserRepository userRepo = new UserRepositoryImplementation();
	public static TransferRepository tRepo = new TransferRepositoryImplementation();

	
	@Override
	public boolean openAccount(User user)  {
        try {
			/*insert into bankaccounts values 
                     (default,0, 'pending', 2); 
                      number, balance, status userId*/
		String sql = "insert into bankaccounts values (default,?,?,?) returning *;";
			
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setFloat(1, 0);
		ps.setString(2, "Pending");
		ps.setInt(3, user.getId());
			
			boolean success = ps.execute();
			
			return success;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<MixUserAccount> getPendingAccounts() {
		
		List<MixUserAccount> mixua = new ArrayList<MixUserAccount>();
		
		try {
			String sql = "select b.accountnumber, u.first_name, u.last_name,"
					+ " b.accountstatus from useraccount u , bankaccounts b "
					+ " where b.accountstatus = ? and b.userid = u.id;";
            
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Pending");
			
			boolean success = ps.execute();
			
			if (success) {
					ResultSet rs = ps.getResultSet();
			
			while (rs.next()) {
				
			  //  System.out.print("Column 1 returned ");
			   // System.out.println(rs.getString(1));

				MixUserAccount smua = new MixUserAccount();
				smua.setAccountNumber(rs.getInt("accountnumber"));
				smua.setFirst(rs.getString("first_name"));
				smua.setLast(rs.getString("last_name"));
				smua.setAccountstatus(rs.getString("accountstatus"));
				
				mixua.add(smua);
			
			}
			return mixua;
			} else {
				System.out.println("not find user data !");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public List<Account> getBalance(User user)  {
           
		List<Account> bankAccounts = new ArrayList<Account>();
		
		try {
		
			String sql = "select * from bankaccounts where userid = ?;"; // input user ID
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Account acct = new Account();
				acct.setAccountNumber(rs.getInt("accountnumber"));
				acct.setBalance(rs.getDouble("accountbalance"));
				acct.setStatus(rs.getString("accountstatus"));
				acct.setUserId(rs.getInt("userid"));
				
				bankAccounts.add(acct);
				
			}

			return bankAccounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public Account getAccountByAccountNumber(int accountNumber)  {
		try {
			
			String sql = "select * from bankaccounts where accountnumber = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, accountNumber);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Account acct = new Account();
				acct.setAccountNumber(rs.getInt("accountnumber"));
				acct.setBalance(rs.getDouble("accountbalance"));
				acct.setStatus(rs.getString("accountstatus"));
				acct.setUserId(rs.getInt("userid"));
				
				return acct;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Account> getAccountByUser(User user)  {
		List<Account> bankAccounts = new ArrayList<Account>();
		
		try {
		
			String sql = "select * from bankaccounts where userid = ?;"; // input user ID
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Account acct = new Account();
				acct.setAccountNumber(rs.getInt("accountnumber"));
				acct.setBalance(rs.getDouble("accountbalance"));
				acct.setStatus(rs.getString("accountstatus"));
				acct.setUserId(rs.getInt("userid"));
				
				bankAccounts.add(acct);
				
			}

			return bankAccounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public List<Account> getAllUserAccountsById(int userId)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccounts()  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean upDateAccountStatus(int account) {
		try {
			
			String sql = "update bankaccounts set accountstatus = 'Active' where accountnumber = ? returning *;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, account);
						
			boolean success = ps.execute();
			
			return success;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public boolean closeAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean upDateAccount(Account account) {
	try {
			
			String sql = "update bankaccounts set accountbalance = ? where accountnumber = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, account.getBalance());
			ps.setInt(2, account.getAccountNumber());
						
			boolean success = ps.execute();
			
			return success;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

}
