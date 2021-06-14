package com.miguel.services;

import java.util.Scanner;

import com.miguel.models.User;

//import models.User;

public interface UserService {
	User login(Scanner Scan);
	void register(Scanner scan);

}
