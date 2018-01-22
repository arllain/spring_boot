package com.arllain.UserRegistrationSystem.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
/*
@Entity
@Table(name = "users")
public class UserInfo {

	@Id
	@GeneratedValue
	@Column(name = "userId")
	private Long id;

	@Column(name = "userName")
	@NotEmpty
	private String userName;

	@Column(name = "password")
	@NotEmpty
	private String password;

	@Column(name = "enabled")
	private boolean isEnabled;

	@Column(name = "role")
	private String role;

	public String getUserName() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
*/