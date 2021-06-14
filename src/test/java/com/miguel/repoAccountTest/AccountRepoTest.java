package com.miguel.repoAccountTest;

import java.sql.Connection;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.miguel.models.User;
import com.miguel.models.Account;
import com.miguel.repositories.TransferRepository;
import com.miguel.repositories.TransferRepositoryImplementation;
import com.miguel.repositories.UserRepository;
import com.miguel.repositories.UserRepositoryImplementation;
import com.miguel.utils.JDBCConnection;

public class AccountRepoTest {
	public static Connection conn = JDBCConnection.getConnection();
	public static UserRepository userRepo = new UserRepositoryImplementation();
	public static TransferRepository tRepo = new TransferRepositoryImplementation();
	

	@Test
	void userTest() {
		//User user = new User(10, "test03","password","test05","test06","test07");
		User user = new User();
		Account account = new Account();
		System.out.println(account.getStatus());
		/*user.setId(8); // testing purposes only
		user.setPassword("gkdwegy");
		userRepo.getUser("user08");
		System.out.println(user);
		
		User user2 = new User(10, "test10","password21","test06","test12","test16");
		user2.setId(18); // testing purposes only
		user2.setPassword("hwiudhw");
		userRepo.getUser("user09");
		System.out.println(user);
		*/
		assertEquals(user, userRepo.getUser(10));
		//assertEquals(user2, userRepo.getUser(3));
	}
	

}
