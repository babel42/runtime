package com.chirpanywhere.framework.integration.Messaging;

import java.util.Map;

public interface Channel {
	Map getProperties();
	void setProperties(Map props);
}
