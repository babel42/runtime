package com.ca.framework.context;

import java.util.HashMap;

import com.ca.framework.data.IValueObject;
import com.ca.framework.data.ValueObject;
import com.ca.framework.utils.Constants;
import org.apache.commons.beanutils.BeanUtils;


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
	public TwilioContext(IValueObject vo) {
		super(null);
		HashMap staticCxt = (HashMap) vo.get(Constants.DYNAMIC_CREATABLE_CONFIG_DATA);
		HashMap dynamicCxt = (HashMap) vo.get(Constants.STATIC_CREATABLE_CONFIG_DATA);
		//BeanUtils.populate(bean, properties);

		//		baseURL = (String) staticCxt.get(Constants.TWILIO_BASE_URL);		
		
		// Need to see if using Bean utils makes sense
		//		accountSid = (String) dynamicCxt.get(Constants.TWILIO_ACCT_SID);
//		authToken = (String) vo.get(Constants.TWILIO_AUTH_TOKEN);
//		fromPhone = (String) vo.get(Constants.SENDER_TEL_NUM);
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
