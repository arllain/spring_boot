package com.arllain.UserRegistrationSystem.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Users")
public class UserDTO {
	
	@Id
	@GeneratedValue
	@Column(name= "USER_ID")
	private long id;
	
	@NotEmpty(message = "error.name.empty")
	@Length(max = 50, message = "error.name.length")
	@Column(name="NAME")
	private String name;
	
	@NotEmpty(message = "error.address.empty")
	@Length(max = 150, message = "error.address.length")
	@Column(name="ADDRESS")
	private String address;
	
	@NotEmpty(message = "error.email.empty")
	@Length(max = 80, message = "error.email.length")
	@Column(name="EMAIL")
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getErrorMessage() {
		return null;
	}
}