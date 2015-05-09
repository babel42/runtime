package com.ca.framework.data;

import java.util.HashMap;
import java.util.Map;

import com.ca.framework.context.IContextData;

public class ValueObject implements IValueObject {
	private Map<String, Object> vo;
	private IContextData ctx;
	
	public ValueObject(IContextData ctx, Map<String, Object> props) {
		
	}
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
	
	public Map<String, Object> getMap() {
		return vo;
	}
	public void setMap(Map<String, Object> vo) {
		this.vo = vo;
	}
}
