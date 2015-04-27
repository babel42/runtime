package com.ca.controller;

import com.ca.utils.data.IValueObject;

public interface IBatchController extends IController{
	public boolean run();
    IValueObject getStoredMetadata(IValueObject vo);
	IValueObject validatePrivelages(IValueObject vo);	
	
}
