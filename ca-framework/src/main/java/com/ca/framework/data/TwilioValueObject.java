package com.ca.framework.data;

import com.ca.framework.context.TwilioContext;
import com.ca.framework.utils.Constants;


public class TwilioValueObject extends ValueObject {
	private String toPhone;
	private Object msg;

	public TwilioValueObject(TwilioContext ctx, String to, Object msg) {
		super(ctx);
		toPhone = to;
		this.msg = msg;
	}
	public TwilioValueObject(IValueObject vo)
	{
		super((TwilioContext)vo.get(Constants.TWILIO_CONTEXT));
		toPhone = (String) vo.get(Constants.DEST_TEL_NUM);
		msg = (String) vo.get(Constants.MESSAGE_VALUE);
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
