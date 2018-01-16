package com.arllain.UserRegistrationSystem.Exception;

import com.arllain.UserRegistrationSystem.dto.UserDTO;

public class CustomErrorType extends UserDTO {
	
	private String errorMessage;
	
	public CustomErrorType(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String getErrorMessage() {
		return errorMessage;
	}
	
}
