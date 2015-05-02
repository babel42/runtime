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
//		transType = vo.get(Constants.TRANSACTION_TYPE);
//		fact = ServiceLocator.getFactory(transType); //?? needed ??
//		fact.create(type) // Cannot work with just the IFacotry as every type of factory may need objects of different class set
		
//		ICreatable transValidator = TransactionFactory.createValidator();
//		ICreatable userAuth = TransactionFactory.createUserAuth();
//		ICreatable 


		return vo;
	}

}
