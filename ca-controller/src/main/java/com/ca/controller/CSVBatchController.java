package com.ca.controller;

import com.ca.framework.data.IValueObject;
import com.ca.framework.data.ValueObject;
import com.ca.framework.errorhandling.CAException;

public class CSVBatchController implements IBatchController {

	@Override
	public IValueObject execute(IValueObject vo) throws CAException{
		// TODO Auto-generated method stub
		return vo;
	}

	private IValueObject getStoredMetadata(IValueObject vo) {
		// TODO Auto-generated method stub
		return vo;
	}

	private IValueObject validatePrivelages(IValueObject vo) {
		return vo;
		// TODO Auto-generated method stub
		
	}
	private void authenticateUser() {
		// TODO Auto-generated method stub
		
	}
	
	private IValueObject validateBatch(IValueObject vo){
		return null;
	}

	
}
