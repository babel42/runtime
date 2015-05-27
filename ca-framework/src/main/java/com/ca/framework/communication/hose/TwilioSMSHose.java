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
import com.ca.framework.utils.Constants;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class TwilioSMSHose implements ICreatable {
	// Find your Account Sid and Token at twilio.com/user/account
	// This should be registered in the static context. 
	public static final String ACCOUNT_SID = "AC589c5fe4aee0746a2285e718c0d8341e"; 
	public static final String AUTH_TOKEN = "7476f459573937aec71b04f768881b91"; 
	public static final String FROM_PHONE = "+16789056928";

	public IValueObject execute(IValueObject vo) throws CAException {
		
		
		
//		TwilioContext ctx = new TwilioContext("https://api.twilio.com/2010-04-01", 
//        "AC589c5fe4aee0746a2285e718c0d8341e", 
//        "7476f459573937aec71b04f768881b91",
//        "+16789056928");
//		TwilioValueObject tvo = new TwilioValueObject(ctx, phone, msg + "@" + new Date().toString());
		
		// Assumes that static context, dynamic context and transaction info is in the vo,
		// static context set by factory and dynamic context/transaction data set by controller
		TwilioContext ctx = new TwilioContext(vo);
		// Putting cooked TwilioContext back in the vo just in case other shumcks down the line need it
		vo.add(Constants.TWILIO_CONTEXT, ctx);
		
		// The controller that called me guarantees that the transaction request data needed is in vo.
		TwilioValueObject tvo = (TwilioValueObject) vo.get(Constants.TWILIO_VO);
		
		
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

	public void configure(IValueObject vo) {
		// Assumes that static context, dynamic context and transaction info is in the vo,
		// static context set by factory and dynamic context/transaction data set by controller
		TwilioContext ctx = new TwilioContext(vo);
		vo.add(Constants.TWILIO_CONTEXT, ctx);	
		
	}
}
