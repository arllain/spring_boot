package com.arllain.UserRegistrationSystem.Exception;

import com.arllain.UserRegistrationSystem.dto.UsersDTO;

public class CustomErrorType extends UsersDTO {
	
	private String errorMessage;
	
	public CustomErrorType(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
}
