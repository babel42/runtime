package com.ca.framework.factory;

import com.ca.framework.data.IValueObject;

public interface IFactory {
	// Takes in transaction type and intent to create the right object of type ICreatable
	// The meta data needed to configure the object is part of the IValueObject or is cached and injected by the factory
	// See each concrete ICreatable for the source of configuration information
	// The caller should populate all information needed form it
	public ICreatable create(IValueObject vo);
}
