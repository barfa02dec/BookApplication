package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.app.validation.ValidateUserData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user")
 /**
 * Class that contains User details 
 *  */
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId",nullable = false)
	private int userId;
	@NotNull
	private String name;
	@NotNull
	private String password;
	@NotNull
	@Column(unique = true)
	private String email;
	@NotNull
	private String contact;
	@NotNull
	private String address;
	@NotNull
	private String role;

	public User() { 
		super();
	}

	public User(int userId, String name, String password, String email,
			String contact, String address, String role) {
		this(name,password,email,contact,address,role);
		ValidateUserData.checkNullValue(userId, "userId");
		this.userId = userId;
		
	}

	public User(@NotNull String name, @NotNull String password, @NotNull String email, @NotNull String contact,
			@NotNull String address, @NotNull String role){
		super();
		ValidateUserData.checkNullValue(name, "name");
		this.name = name;
		ValidateUserData.checkNullValue(password, "password");
		this.password = password;
		ValidateUserData.checkNullValue(email, "email");
		this.email = email;
		ValidateUserData.checkNullValue(contact, "contact");
		this.contact = contact;
		ValidateUserData.checkNullValue(address, "address");
		this.address = address;
		ValidateUserData.checkNullValue(role, "role");
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		ValidateUserData.checkNullValue(userId, "userId");
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name){
		ValidateUserData.checkNullValue(name, "name");
		this.name = name;
	}
	@JsonIgnore
	@JsonProperty(value = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		ValidateUserData.checkNullValue(password, "password");
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		ValidateUserData.checkNullValue(email, "email");
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		ValidateUserData.checkNullValue(contact, "contact");
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		ValidateUserData.checkNullValue(address, "address");
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		ValidateUserData.checkNullValue(role, "role");
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", contact=" + contact + ", address=" + address + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
}