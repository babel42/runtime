package com.ca.framework.data;

import java.util.HashMap;

import com.ca.framework.context.IContextData;

public class ValueObject implements IValueObject {
	private HashMap vo;
	private IContextData ctx;
	
	public ValueObject(IContextData ctx) {
		vo = new HashMap<String, Object>();
		this.ctx = ctx;
	}
	
	public void printContents()
	{
		
	}

	public Object get(String key) {
		return vo.get(key);
	}

	public void add(String key, Object value) {
		vo.put(key, value);
	}

	public IContextData getContext() {
		return ctx;
	}

	public void setContext(IContextData data) {
		ctx = data;
	}
}
