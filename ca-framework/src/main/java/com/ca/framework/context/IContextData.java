package com.ca.framework.context;

public interface IContextData {
	public Object get(String key);
	public void add(String key, Object value);
}
