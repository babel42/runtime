package com.ca.utils.data;

import java.util.HashMap;

public class ValueObject implements IValueObject {
	private HashMap vo;
	
	public ValueObject() {
		vo = new HashMap<String, Object>();
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
}
