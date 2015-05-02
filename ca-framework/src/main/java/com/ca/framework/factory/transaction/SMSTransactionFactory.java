package com.ca.framework.factory.transaction;

import com.ca.framework.data.IValueObject;
import com.ca.framework.utils.Constants;

public class SMSTransactionFactory extends TransactionFactory {
	String type = Constants.TRANSACTION_TYPE_SMS;

	@Override
	public IValueObject createValidator(IValueObject vo) {

		return null;
	}

	@Override
	public IValueObject createAdInjector(IValueObject vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IValueObject createChannelSender(IValueObject vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IValueObject createChannelReceiver(IValueObject vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IValueObject createPersistenceManager(IValueObject vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
