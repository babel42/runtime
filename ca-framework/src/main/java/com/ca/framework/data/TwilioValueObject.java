package com.ca.framework.data;

import com.ca.framework.context.TwilioContext;


public class TwilioValueObject extends ValueObject {
	private String toPhone;
	private Object msg;

	public TwilioValueObject(TwilioContext ctx, String to, Object msg) {
		super(ctx);
		toPhone = to;
		this.msg = msg;
	}
	
	public String getToPhone() {
		return toPhone;
	}

	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}
}
