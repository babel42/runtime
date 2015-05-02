package com.ca.controller;

import com.ca.framework.data.IValueObject;
import com.ca.framework.errorhandling.CAException;

public interface IBatchController extends IController{
	public IValueObject execute(IValueObject vo) throws CAException;	
	
}
