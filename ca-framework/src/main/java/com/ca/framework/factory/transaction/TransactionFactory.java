package com.ca.framework.factory.transaction;

import com.ca.framework.factory.ICreatable;
import com.ca.framework.factory.IFactory;
import com.ca.framework.factory.ReceiveChannelFactory;
import com.ca.framework.factory.SendChannelFactory;
import com.ca.framework.factory.ValidatorFactory;
import com.ca.framework.utils.Constants;
import com.ca.utils.data.IValueObject;

public class TransactionFactory {
	static TransactionFactory instance = null;
	static SendChannelFactory sendChannelFact = null;
	static ReceiveChannelFactory recvChannelFact=null;
	
	
	private TransactionFactory() {
		initializeValidatorFactory();
		initializeAdInjectinoFactory();
		intializeChannelSenderFactory();
		initializeChannelReseverFactory();
	}

	private void initializeChannelReseverFactory() {
		// TODO Auto-generated method stub
		
	}

	private void intializeChannelSenderFactory() {
		// TODO Auto-generated method stub
		
	}

	private void initializeAdInjectinoFactory() {
		// TODO Auto-generated method stub
		
	}

	private void initializeValidatorFactory() {
		// TODO Auto-generated method stub
		
	}

//	public static TransactionFactory getInstance() {
//		synchronized (instance) {
//			if (null == instance)
//				instance = new TransactionFactory();
//		}
//		return instance;
//	}

//	public ICreatable createValidator(IValueObject vo) {
//		return ValidatorFactory.create(IValueObject
//				.get(Constants.TRANSACTION_TYPE));
//	}

	public ICreatable createAdInjector(IValueObject vo) {
		return null;
	}

	public ICreatable createChannelSender(IValueObject vo) {
		return null;
	}

	public ICreatable createChannelReceiver(IValueObject vo) {
		return null;
	}

	public ICreatable createPersistenceManager(IValueObject vo) {
		return null;
	}
}
