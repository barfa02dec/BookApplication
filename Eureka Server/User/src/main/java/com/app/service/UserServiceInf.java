package com.app.service;

import com.app.exception.BookException;
import com.app.model.User;

public interface UserServiceInf {
	/**************** Create ******************/
	public User addUser(User user) throws BookException;
	
	
	/***************** Read *********************/
	public User getUserById(int userId) throws BookException;
	public void checkDuplicateEmail(String email) throws BookException;
	public User logIn(String email,String password) throws BookException;
	
	
	/******************** Update *********************/
	public User updateUser(User user) throws BookException;
	
	
	/**************** Delete *******************/
	public boolean removeUser(int userId) throws BookException;
}
