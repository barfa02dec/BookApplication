package com.app.validation;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

import com.app.exception.BookException;

public class ValidateUserData {
	
	public static void checkNullValue(Object obj,String field) throws BookException {
		try{
			if(obj instanceof String) 
				checkZeroLength((String)obj,field);
		Optional<Object> optional_obj = Optional.ofNullable(obj);
		optional_obj.orElseThrow(() -> new BookException( HttpStatus.UNPROCESSABLE_ENTITY,field + " is null"));
		
		if(field == "email")
			validateEmail((String) obj);
		else if(field == "password")
			validatePassword((String) obj);
		else if(field == "name")
			validateName((String) obj);
		else if(field=="role")
			validateRole((String) obj);
		else if(field=="contact")
			validateContact((String) obj);
		}catch(BookException e) {
			throw e;
		}
	} 

	public static void validateEmail(String email) throws BookException {
		String regex = "^(.+)@(.+)$"; 
		Pattern pattern = Pattern.compile(regex); 
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()) 
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid email");
	}
	
	public static void validatePassword(String password) throws BookException {
		if(password.length()>10 || password.length()<5)
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid Password Length");
	}
	
	public static void validateRole(String role) throws BookException {
		if(!role.equals("ServiceProvider") && !role.equals("User"))
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid role Value");
	}
	
	public static void validateContact(String contact) throws BookException {
		if(!contact.matches("[0-9]+"))
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid contact\nContact contains only digits");
		if(contact.length()>12||contact.length()<8)
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid contact length");
	}
		
	public static void validateName(String name) throws BookException {
		if(!name.matches("[a-zA-Z]*$"))
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Name contains only Alphabets");
		if(name.length()>15 || name.length()<3)
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid length of name");
	}
	
	public static void checkZeroLength(String obj , String field) throws BookException{
		if(obj.length()==0)
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY,field + " Length 0 not allowed");
	} 
	
}