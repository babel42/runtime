package com.ca.framework.communication.hose;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.ca.framework.context.TwilioContext;
import com.ca.framework.data.IValueObject;
import com.ca.framework.data.TwilioValueObject;
import com.ca.framework.errorhandling.CAException;
import com.ca.framework.factory.ICreatable;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class TwilioSMSHose implements ICreatable {
	// Find your Account Sid and Token at twilio.com/user/account 
	public static final String ACCOUNT_SID = "AC589c5fe4aee0746a2285e718c0d8341e"; 
	public static final String AUTH_TOKEN = "7476f459573937aec71b04f768881b91"; 
	public static final String FROM_PHONE = "+16789056928";

	public IValueObject execute(IValueObject vo) throws CAException {
		TwilioContext ctx = (TwilioContext) vo.getContext();
		TwilioValueObject tvo = (TwilioValueObject) vo; // the factory that created me guarantees this.
		System.out.println("TwilioSMSHose.send(): received ["+tvo.getToPhone() +"/"+tvo.getMsg()+"]");
		TwilioRestClient client = new TwilioRestClient(ctx.getAccountSid(), ctx.getAuthToken()); 
		System.out.println("TwilioSMSHose.send(): created client");

		// Build the parameters 
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("To", tvo.getToPhone())); 
		params.add(new BasicNameValuePair("From", ctx.getFromPhone())); 
		params.add(new BasicNameValuePair("Body", tvo.getMsg().toString()));   

		MessageFactory messageFactory = client.getAccount().getMessageFactory(); 
		Message message;
		try {
			message = messageFactory.create(params);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			throw new CAException(e, e.getLocalizedMessage(), 1024);
		} 
		String msgSid = message.getSid();
		System.out.println(msgSid); 
		tvo.add("TWILIO_MESSAGE_SID", msgSid);
		return tvo;
	}
}
