package com.vncodelab.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = -2581975292273282583L;

	private String errorMessage;

	private String errorCode;

	public NotFoundException() {};

	public NotFoundException(String errorMessage, String errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
