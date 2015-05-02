package com.ca.framework.context;

import com.ca.framework.data.ValueObject;


public class TwilioContext extends ContextData {
	
	private String accountSid;
	private String authToken;
	private String fromPhone;
	private String baseURL;
	
	public TwilioContext(String url, String sid, String auth, String from) {
		super(null);
		baseURL = url;
		accountSid = sid;
		authToken = auth;
		fromPhone = from;
	}

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

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
}
