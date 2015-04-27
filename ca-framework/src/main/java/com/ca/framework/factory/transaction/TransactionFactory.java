package com.ca.framework.factory.transaction;

import com.ca.utils.data.IValueObject;

public abstract class TransactionFactory {
	public abstract IValueObject createValidator(IValueObject vo);
	public abstract IValueObject createAdInjector(IValueObject vo);
	public abstract IValueObject createChannelSender(IValueObject vo);
	public abstract IValueObject createChannelReceiver(IValueObject vo);
	public abstract IValueObject createPersistenceManager(IValueObject vo);
}
