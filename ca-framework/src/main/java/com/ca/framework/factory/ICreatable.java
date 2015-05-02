package com.ca.framework.factory;

import com.ca.framework.errorhandling.CAException;
import com.ca.utils.data.IValueObject;

public interface ICreatable {
	public IValueObject execute(IValueObject vo) throws CAException;

}
