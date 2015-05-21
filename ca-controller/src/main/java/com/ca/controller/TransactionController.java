package com.ca.controller;

import com.ca.framework.data.IValueObject;
import com.ca.framework.errorhandling.CAException;
import com.ca.framework.factory.ICreatable;
import com.ca.framework.factory.IFactory;
import com.ca.framework.factory.transaction.TransactionFactory;
import com.ca.framework.utils.Constants;

public class TransactionController implements ITransactionController {
	String transType;
	IFactory fact;

	@Override
	public IValueObject execute(IValueObject vo) throws CAException {
		TransactionFactory fact = TransactionFactory.getInstance(vo);
		ICreatable hose = fact.createObject(Constants.TRANSACTION_TYPE_SMS, "hose", vo);
		IValueObject respVo = hose.execute(vo);

		return respVo;
	}

}
