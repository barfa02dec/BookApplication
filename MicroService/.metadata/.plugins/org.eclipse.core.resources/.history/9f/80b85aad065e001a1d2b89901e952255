package com.app.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.model.User;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	/**
	 * @param email
	 * @param password
	 * @return User Object
	 */
	@Query("SELECT u FROM User u WHERE u.email=:email AND u.password=:password")
	public User logIn(@Param("email") String email,@Param("password") String password);
	/**
	 * @param email
	 * @return User object
	 */
	@Query("SELECT u FROM User u WHERE u.email=:email")
	public User getUserByEmail(@Param("email")String email);
}
