package com.miguel.repositories;

import java.util.List;

import com.miguel.models.User;

//CRUD = Create Read Update Delete

public interface UserRepository {
	User addUser(User u);
	User getUser(Integer i);
	User getUser(String username);
	User getUser(String user, String pass);
	List<User> getUsers();
	boolean removeUser(User u);
	public boolean isUserExsit(String username);
	public boolean isUserExsitbyfirstlast(String first, String last);
	User getUserbyname(String first, String last);
	

}
