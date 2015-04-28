package com.ca.framework.errorhandling;

import com.ca.utils.data.IValueObject;

public class CAException extends Throwable {
	private CAError error = null;
	private IValueObject context;

	public CAException(Throwable ex, String sourceMsg, CAError err) {
		super(sourceMsg, ex);
		error=err;
	}

	public CAException(Throwable ex, String sourceMsg, int errorCode) {
		super(sourceMsg, ex);
		error.setErrorCode(errorCode);
	}
	public CAException(Throwable ex, String sourceMsg, CAError err, IValueObject cxt) {
		super(sourceMsg, ex);
		error=err;
		context = cxt;
	}

	public CAError getError() {
		return error;
	}

	public void setError(CAError error) {
		this.error = error;
	}

	public IValueObject getContext() {
		return context;
	}

	public void setContext(IValueObject context) {
		this.context = context;
	}
	
}
