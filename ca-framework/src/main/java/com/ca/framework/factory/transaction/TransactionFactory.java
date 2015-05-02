package com.ca.framework.factory.transaction;

import com.ca.framework.data.IValueObject;
import com.ca.framework.factory.ICreatable;
import com.ca.framework.factory.IFactory;
import com.ca.framework.factory.ReceiveChannelFactory;
import com.ca.framework.factory.SendChannelFactory;
import com.ca.framework.factory.ValidatorFactory;
import com.ca.framework.utils.Constants;

public class TransactionFactory {
	static TransactionFactory instance = null;
	static ValidatorFactory validatorFact = null;
	static SendChannelFactory sendChannelFact = null;
	static ReceiveChannelFactory recvChannelFact = null;
	private String transType;

	private TransactionFactory(IValueObject vo) {
		transType = vo.get(Constants.TRANSACTION_TYPE);
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

	public static TransactionFactory getInstance(IValueObject vo) {
		synchronized (instance) {
			if (null == instance)
				instance = new TransactionFactory(vo);
		}
		return instance;
	}

//	public ICreatable createValidator(IValueObject vo) {
//		return ValidatorFactory.create(IValueObject
//				.get(Constants.TRANSACTION_TYPE));
//	}

	public IFactory getAdInjector(IValueObject vo) {
		return vo;
	}

	public IFactory getChannelSender(IValueObject vo) {
		return null;
	}

	public IFactory getChannelReceiver(IValueObject vo) {
		return null;
	}

	public IFactory getPersistenceManager(IValueObject vo) {
		return null;
	}
}
