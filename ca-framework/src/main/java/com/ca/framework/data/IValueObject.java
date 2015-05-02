package com.ca.framework.data;

import com.ca.framework.context.IContextData;

public interface IValueObject {
	public Object get(String key);
	public void add(String key, Object value);
	public IContextData getContext();
	public void setContext(IContextData data);
}
