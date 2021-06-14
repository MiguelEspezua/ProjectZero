package com.miguel.services;

import java.util.Scanner;

import com.miguel.models.User;
import com.miguel.repositories.UserRepository;
import com.miguel.repositories.UserRepositoryImplementation;

public class UserServiceImplementation implements UserService {

	private UserRepository ua = new UserRepositoryImplementation();  
	
	@Override
	public User login(Scanner scan) {
		
		System.out.println("Input Username: ");
		String username = scan.nextLine();
		
		
		if (!ua.isUserExsit(username)){ 
	    	System.out.println("Incorrect User! ...");
	    	return null;
	    } else {
	    	User checkuser = ua.getUser(username);
			//String tpass = checkuser.getPassword();
		    //System.out.println(tpass);
		System.out.println("Input Password: ");
		String password = scan.nextLine();
		
	    if (checkuser.getPassword().equals(password))
		    return checkuser;
	    else { 
	    	System.out.println("Incorrect Password! ...");
	        return null;     
	    }
	   	    
	  }
	}

	@Override
	public void register(Scanner scan) {
		User u = new User();
		u.setType("Customer");
		System.out.println("Enter username:  ");
		//String username = scan.nextLine();
		// check user NOT exist

		//System.out.println(ua.isUserExsit(username));
		u.setUsername(scan.nextLine());
		if (!ua.isUserExsit(u.getUsername())) {     // check is user exist
			System.out.println(u.getUsername());
		//u.setUsername(username);
		System.out.println("Enter password:  ");
		u.setPassword(scan.nextLine());
		System.out.println("Enter first name:  ");
		u.setFirst(scan.nextLine());
		System.out.println("Enter last name:  ");
		u.setLast(scan.nextLine());
		
		System.out.println(u.getUsername()+": "+ u.getFirst()+" "+u.getLast());
		//System.out.println(u.toString());  // test input data
		System.out.println("Do you wish to save the following information [y/n]:");
		String response = scan.nextLine();
		if ("y".equalsIgnoreCase(response)) {
			ua.addUser(u);
			System.out.println("add values user complete ..\n");
			if(u.getId()!=0)
				System.out.println("User information saved. => Login");
			else
				System.out.println("Save failed, Try register again");
			} else {
				System.out.println("User information not saved.");
			}
	    } else {
	    	System.out.println("The username exist! ...\n Try gain ..");
	    	
	    	
	    }
	}
}
