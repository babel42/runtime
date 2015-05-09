package com.ca.framework.context;

import java.util.Map;

import com.ca.framework.data.ValueObject;

public class ContextData extends ValueObject implements IContextData {

	public ContextData(IContextData ctx) {
		super(ctx);
	}
	
	public ContextData(IContextData ctx, Map<String, Object> map) {
		super(ctx, map);
	}
}
