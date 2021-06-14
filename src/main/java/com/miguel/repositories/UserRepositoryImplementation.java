package com.miguel.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.miguel.models.User;
import com.miguel.utils.JDBCConnection;

public class UserRepositoryImplementation implements UserRepository {
	
	private static Connection conn = JDBCConnection.getConnection();

	@Override
	public User addUser(User u) {
		// example (default, 'user02', 'pass02', 'John', 'Accrimgton', 'Customer'),
		String sql = "insert into useraccount values (default, ?,?, ?, ?, ?) returning *;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirst());
			ps.setString(4, u.getLast());
			ps.setString(5, u.getType());
			
			boolean success = ps.execute();
			
			if (success) {
			
			ResultSet rs = ps.getResultSet();
			
			if (rs.next()) {
				u.setId(rs.getInt("id"));
				return u;
			}
		  }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUser(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String username, String pass) {
		//String sql = "select * from useraccount where username = ?;";
		try {
			
			String sql = "select * from useraccount where username = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			
			boolean success = ps.execute();
			
			if (success) {
					//ResultSet rs = ps.executeQuery();
				ResultSet rs = ps.getResultSet();
			 
			
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirst(rs.getString("first_name"));
				u.setLast(rs.getString("last_name"));

				u.setType(rs.getString("type"));

				
				return u;
			 }
			} else {
				System.out.println("not find user data !");
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("getuser return null  ..\n");
		return null;
	}
	
	@Override
	public User getUserbyname(String first, String last) {
			

        try {
			
			String sql = "select * from useraccount where first_name = ? and last_name = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
	        
	            ps.setString(1, first);
	            ps.setString(2, last);
	
			
			boolean success = ps.execute();
			
			if (success) {
					
				ResultSet rs = ps.getResultSet();
			 
			
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirst(rs.getString("first_name"));
				u.setLast(rs.getString("last_name"));

				u.setType(rs.getString("type"));

				
				return u;
			 }
			} else {
				System.out.println("not find user data !");
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("getuser return null  ..\n");
		return null;
	
	}
	
	
	@Override
	public User getUser(String username) {
		//String sql = "select * from useraccount where username = ?;";
		try {
			
			String sql = "select * from useraccount where username = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirst(rs.getString("first_name"));
				u.setLast(rs.getString("last_name"));

				u.setType(rs.getString("type"));

				
				return u;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isUserExsit(String username) {

		   boolean isDuplicated = false;

		   String sql = "select * from useraccount where username = ?;";
			

		    try {
		        PreparedStatement statement = conn.prepareStatement(sql);
		        if (statement != null) {
		            statement.setString(1, username);




		            try {
		                ResultSet results = statement.executeQuery();
		                if (results != null) {
		                    try {
		                        if (results.next()) {
		                            isDuplicated = true;

		                        }
		                    } catch (Exception resultSetException) {
		                        resultSetException.printStackTrace();
		                    }
		                    results.close();
		                }
		            } catch (Exception statmentExcption) {
		                statmentExcption.printStackTrace();
		            }
		            statement.close();
		        }
		    } catch (Exception generalException) {
		        generalException.printStackTrace();
		    }


		    return isDuplicated;
		}

	@Override
	public boolean isUserExsitbyfirstlast(String first, String last) {

		   boolean isDuplicated = false;

		   String sql = "select * from useraccount where first_name = ? and last_name = ?;";
			

		    try {
		        PreparedStatement statement = conn.prepareStatement(sql);
		        if (statement != null) {
		            statement.setString(1, first);
		            statement.setString(2, last);




		            try {
		                ResultSet results = statement.executeQuery();
		                if (results != null) {
		                    try {
		                        if (results.next()) {
		                            isDuplicated = true;

		                        }
		                    } catch (Exception resultSetException) {
		                        resultSetException.printStackTrace();
		                    }
		                    results.close();
		                }
		            } catch (Exception statmentExcption) {
		                statmentExcption.printStackTrace();
		            }
		            statement.close();
		        }
		    } catch (Exception generalException) {
		        generalException.printStackTrace();
		    }

		    
		    return isDuplicated;
		}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

}
