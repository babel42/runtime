package com.ca.controller;

import com.ca.framework.errorhandling.CAException;
import com.ca.utils.data.IValueObject;

public interface IBatchController extends IController{
	public IValueObject execute(IValueObject vo) throws CAException;	
	
}
