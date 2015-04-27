package com.ca.controller;

import com.ca.utils.data.IValueObject;

public class JSONBatchController implements IBatchController{

	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IValueObject getStoredMetadata(IValueObject vo) {
		// TODO Auto-generated method stub
return vo;
	}

	@Override
	public IValueObject validatePrivelages(IValueObject vo) {
		// TODO Auto-generated method stub
		return vo;
		
	}

}
