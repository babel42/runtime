package com.ca.utils.data.communication;

import com.ca.utils.data.ValueObject;

public class TwilioValueObject extends ValueObject {
	private String accountSid;
	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getFromPhone() {
		return fromPhone;
	}

	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
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

	private String authToken;
	private String fromPhone;
	private String toPhone;
	private Object msg;

	public TwilioValueObject(String sid, String auth, String from, String to, Object msg) {
		accountSid = sid;
		authToken = auth;
		fromPhone = from;
		toPhone = to;
		this.msg = msg;
	}
}
