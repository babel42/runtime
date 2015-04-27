package com.ca.controller;

import com.ca.utils.data.IValueObject;

public interface ITransactionController extends IController {
	IValueObject execute(IValueObject VO);
}
