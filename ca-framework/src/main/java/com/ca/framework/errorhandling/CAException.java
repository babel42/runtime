package com.ca.framework.errorhandling;

public class CAException extends Throwable {
	private CAError error = null;

	public CAException(Throwable ex, String sourceMsg, CAError err) {
		super(sourceMsg, ex);
		error=err;
	}

	public CAException(Throwable ex, String sourceMsg, int errorCode) {
		super(sourceMsg, ex);
		error.setErrorCode(errorCode);
	}
}
