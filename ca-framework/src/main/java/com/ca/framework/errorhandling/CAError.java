package com.ca.framework.errorhandling;

public class CAError {
	private int errorCode;
	private String message;

	public CAError(int err, String msg) {
		errorCode = err;
		message = msg;
	}

	public void setErrorCode(int err) {
		errorCode = err;
	}

}
