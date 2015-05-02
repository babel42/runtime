package com.ca.framework.context;

import com.ca.framework.data.ValueObject;

public class ContextData extends ValueObject implements IContextData {

	public ContextData(IContextData ctx) {
		super(ctx);
	}
}
