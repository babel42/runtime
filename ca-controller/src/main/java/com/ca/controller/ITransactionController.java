package com.ca.controller;

import com.ca.framework.data.IValueObject;
import com.ca.framework.errorhandling.CAException;

public interface ITransactionController extends IController {
	IValueObject execute(IValueObject VO) throws CAException;
}
