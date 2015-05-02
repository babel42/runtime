package com.ca.framework.communication.hose;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.ca.framework.factory.ICreatable;
import com.ca.utils.data.IValueObject;
import com.ca.utils.data.communication.TwilioValueObject;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class TwilioSMSSender implements ICreatable {
	// Find your Account Sid and Token at twilio.com/user/account 
	public static final String ACCOUNT_SID = "AC589c5fe4aee0746a2285e718c0d8341e"; 
	public static final String AUTH_TOKEN = "7476f459573937aec71b04f768881b91"; 

	public void send(String phone, String msg) throws TwilioRestException {
		System.out.println("TwilioSMSSender.send(): received ["+phone +"/"+msg+"]");
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN); 
		System.out.println("TwilioSMSSender.send(): created client");

		// Build the parameters 
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("To", phone)); 
		params.add(new BasicNameValuePair("From", "+16789056928")); 
		params.add(new BasicNameValuePair("Body", msg));   

		MessageFactory messageFactory = client.getAccount().getMessageFactory(); 
		Message message = messageFactory.create(params); 
		System.out.println(message.getSid()); 
	}

	public IValueObject execute(IValueObject vo) {
		// TODO Auto-generated method stub
		TwilioValueObject tvo = (TwilioValueObject) vo; // the factory that created me guarantees this.
		
		return null;
	}
}
