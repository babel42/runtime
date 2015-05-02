package com.ca.framework.factory;

import com.ca.utils.data.IValueObject;

public interface IFactory {
	public ICreatable create(IValueObject vo);
}
