package com.arllain.UserRegistrationSystem.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users_roles")
public class UserInfo {

	@Id
	@GeneratedValue
	@Column(name= "USER_ID")
	private long id;

	@Column(name = "USER_NAME")
	@NotEmpty
	private String userName;

	@Column(name = "PASSWORD")
	@NotEmpty
	private String password;

	@Column(name = "ENABLED")
	private boolean isEnabled;

	@Column(name = "ROLE")
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
