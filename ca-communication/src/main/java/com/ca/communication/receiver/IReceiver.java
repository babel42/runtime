package com.ca.communication.receiver;

import com.ca.framework.errorhandling.CAException;
import com.ca.utils.data.IValueObject;

public interface IReceiver {
	IValueObject execute(IValueObject vo) throws CAException;
}
