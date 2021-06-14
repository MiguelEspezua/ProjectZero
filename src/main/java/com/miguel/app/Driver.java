package com.miguel.app;

import java.util.Scanner;

import com.miguel.models.User;
import com.miguel.services.AccountService;
import com.miguel.services.AccountServiceImplementation;
import com.miguel.services.TransferService;
import com.miguel.services.TransferServiceImplementation;
import com.miguel.services.UserService;
import com.miguel.services.UserServiceImplementation;
import com.miguel.utils.AppLogger;


public class Driver {
	private static Scanner scan = new Scanner(System.in);
	private static User loggedUser;
	private static UserService us = new UserServiceImplementation();
	private static TransferService ts = new TransferServiceImplementation();
	private static AccountService as = new AccountServiceImplementation();

	public static void main(String[] args) {
		AppLogger.logger.info("Program Started.");
		scan = new Scanner(System.in);
		boolean run = true;
		while(run) {
			System.out.print("+----------------------------+\n");
			System.out.print("|   Welcome Bank Assistant   |\n");
			System.out.print("+----------------------------+\n");
			System.out.println("\033[1;32m    Select an option \u001B[0m");
			System.out.println("1. Register\n2. Login\n3. Quit");
			String option = scan.nextLine();
			AppLogger.logger.info(option);
			switch (option) {
			case "1":
				us.register(scan);
				break;
			case "2":
				login();
				break;
			case "3":
				run = false;
				System.out.println("Have a great day Bye!");
				AppLogger.logger.info("Program ended.");
				break;
			default:
				System.out.println("Try again!");
				break;
			}
		}
		scan.close();

	}
	private static void login() {
		loggedUser = us.login(scan);
		if (loggedUser == null) {
			System.out.println("Incorrect Login. User not exist");
		} else {
			
			System.out.println("Login complete. Welcome \n" + loggedUser.getFirst()+" "+loggedUser.getLast());
			switch(loggedUser.getType()) {
			case "Customer": customerOptions(); break;
			case "Employee": employeeOptions(); break;
			default: break;
			}
		}
	}
	private static void employeeOptions() {
		// check pending application for approve
		AppLogger.logger.info(loggedUser.getFirst());
		boolean run = true;
		while(run) {
		System.out.println("Employee Option");
		System.out.println("1. approve or reject \n2. view a customer's bank accounts.\n"
				+ "3. view a log of all transaction \n4. Logout");
		
		String option = scan.nextLine();
		switch(option) {
		case "1":
			as.viewPendingAccounts(); // As an employee, I can approve or reject an account.
			break;
		case "2": {
			System.out.println("input customer First name :");
			String firstname = scan.nextLine();
			System.out.println("Input curtomer Last Name :");
			String lastname = scan.nextLine();
			as.viewCustomerAccount(firstname, lastname);  //As an employee, I can view a customer's bank accounts.
			break;
		}
		case "3":
			ts.viewAllTransfers();   // A an employee, I can view a log of all transactions.  
			break;
		case "4":
			run = false;
			loggedUser = null;
			break;
		}
		}
	}
    
	private static void customerOptions() {
		// Open account [save, checking];
		// operation [deposit, withdraw]
		// transfer [view pending, [accept, reject], Cancel]
		boolean run = true;
		while(run) {
		System.out.println("\033[1;32m Select an option\u001B[0m");
		System.out.println("1. Apply Account\n2. View an Account\n"
				+ "3. withdrawal \n4. Deposit \n5. Transfer \n6.Logout");
		String option = scan.nextLine();
			switch(option) {
			case "1":
				as.openAccount(loggedUser); // As a customer, I can apply for a new bank account with a starting balance.
				break;
			case "2":
				as.viewBalance(loggedUser);  //As a customer, I can view the balance of a specific account
				break;
			case "3": { //WITHDRAW operation
				System.out.println("input account number :");
				int accountnumber = scan.nextInt();
				System.out.println("Input withdraw amount :");
				double amount = scan.nextDouble();
				as.withdraw(accountnumber, amount);//ts.transfer(scan); //As a customer, I can make a withdrawal or deposit to a specific account.
				break;
			}
			case "4":{ //DEPOSIT operation
				System.out.println("input account number :");
				int accountnumber = scan.nextInt();
				System.out.println("Input deposit amount :");
				double amount = scan.nextDouble();
				as.deposit(accountnumber, amount);// I can post a money transfer to another account.
				break;
			}
			case "5":{
				System.out.println("input origen account number :");
				int origenaccountnumber = scan.nextInt();
				System.out.println("input destination account number :");
				int destinationaccountnumber = scan.nextInt();
				System.out.println("Input deposit amount :");
				double amount = scan.nextDouble();
				as.transfer(origenaccountnumber,destinationaccountnumber,amount);//  //As a customer, I can accept a money transfer from another account.
				break;
			}
			case "6":
				run = false;
				loggedUser = null;
				break;
		}
		}
	}
}
