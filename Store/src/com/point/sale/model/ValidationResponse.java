package com.point.sale.model;

public class ValidationResponse {
	
	private Boolean validInput;
	
	private String validationError;
	
	public ValidationResponse(Boolean valid, String error) {
		this.validationError = error;
		this.validInput = valid;
	}

	public Boolean getValidInput() {
		return validInput;
	}

	public void setValidInput(Boolean validInput) {
		this.validInput = validInput;
	}

	public String getValidationError() {
		return validationError;
	}

	public void setValidationError(String validationError) {
		this.validationError = validationError;
	}

}
