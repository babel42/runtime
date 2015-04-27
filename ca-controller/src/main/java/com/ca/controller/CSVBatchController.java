package com.ca.controller;

import com.ca.utils.data.IValueObject;
import com.ca.utils.data.ValueObject;

public class CSVBatchController implements IBatchController {

	@Override
	public IValueObject execute(IValueObject vo) {
		// TODO Auto-generated method stub
		return vo;
	}

	@Override
	public IValueObject getStoredMetadata(IValueObject vo) {
		// TODO Auto-generated method stub
		return vo;
	}

	public IValueObject validatePrivelages(IValueObject vo) {
		return vo;
		// TODO Auto-generated method stub
		
	}
	public void authenticateUser() {
		// TODO Auto-generated method stub
		
	}
	
	public IValueObject validateBatch(IValueObject vo){
		return new ValueObject();
	}

	
}
