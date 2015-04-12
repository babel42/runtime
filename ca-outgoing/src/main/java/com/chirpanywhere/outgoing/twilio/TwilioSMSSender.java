package com.chirpanywhere.outgoing.twilio;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class TwilioSMSSender {
	// Find your Account Sid and Token at twilio.com/user/account 
	public static final String ACCOUNT_SID = "AC589c5fe4aee0746a2285e718c0d8341e"; 
	public static final String AUTH_TOKEN = "7476f459573937aec71b04f768881b91"; 

	public void send(String msg) throws TwilioRestException {
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN); 

		// Build the parameters 
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("To", "+14045791180")); 
		params.add(new BasicNameValuePair("From", "+16789056928")); 
		params.add(new BasicNameValuePair("Body", msg));   

		MessageFactory messageFactory = client.getAccount().getMessageFactory(); 
		Message message = messageFactory.create(params); 
		System.out.println(message.getSid()); 
	} 

	public static void main(String[] args) throws TwilioRestException {
		TwilioSMSSender sender = new TwilioSMSSender();
		sender.send("Hi from java");
	}
}
