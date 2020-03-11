package com.app.service;

import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.app.exception.BookException;
import com.app.model.User;
import com.app.repository.UserRepository;

@Service
public class UserServiceImp implements UserServiceInf{

	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderServiceInf orderService;
	/************************** Create *********************************/
	/**
	 * Input - User data
	 * check is email already exist
	 * add user data into user table
	 * return added user except password
	 * @throws BookException
	 */
	@Override
	public User addUser(User user){
		try{
			this.checkDuplicateEmail(user.getEmail());
			return userRepository.save(user);
		}catch(ConstraintViolationException cve) {
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY, "Null value can not be insert");
		}catch (BookException e) {
			throw e;
		}
	}

	/****************************** Read **********************************/
	/**
	 * Taking user id 
	 * return user details 
	 * @throws BookException
	 * */
	@Override
	public User getUserById(int userId){
		return userRepository.findById(userId).orElseThrow(()->new BookException (HttpStatus.NOT_FOUND,"Invalid User id"));
	}
	/**
	 * Input email
	 * return user details
	 * @throws BookException
	 * */
	@Override
	public void checkDuplicateEmail(String email){
		if(userRepository.getUserByEmail(email) != null)
			throw new BookException(HttpStatus.NOT_ACCEPTABLE,"Email already exist");
	}
	/**
	 * Input email and password 
	 * return User for same email and password
	 * @throws BookException
	 */
	@Override
	public User logIn(String email,String password){
		User user = userRepository.logIn(email, password);
		if(user==null)
			throw new BookException(HttpStatus.UNAUTHORIZED,"Invalid email and password");
		return user;
	}
	
	/************************** Update ***********************************/
	/**
	 * Taking new details of user with same user_id
	 * Replace old user by new user data if old user available except email  
	 * returns updated user details
	 * @throws BookException
	 * */
	@Override
	public User updateUser(User user) throws BookException {	
		user.setEmail(this.getUserById(user.getUserId()).getEmail());
		return userRepository.save(user);
	}	
	/*************************** Delete *****************************/

	/**
	 * Taking user id
	 * first delete user data from order_detail and booking table table if available 
	 * delete user from user data
	 * return true on success
	 * @throws BookException
	 * */
	@Override
	public boolean removeUser(int userId){
		try {
			orderService.removeAllOrderOfAnUser(userId);
		}catch (BookException e) {}
		try {
			userRepository.deleteById(userId);
			return true;
		}catch(EmptyResultDataAccessException erdae) {
			throw new BookException(HttpStatus.NOT_FOUND,"Invalid User");
		}
	}

}
