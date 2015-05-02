package com.ca.framework.factory;

import com.ca.framework.data.IValueObject;
import com.ca.framework.errorhandling.CAException;

public interface ICreatable {
	public IValueObject execute(IValueObject cvo) throws CAException;

}
