package com.ca.framework.integration.Messaging;

import java.util.Map;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.RecordMetadata;

import com.ca.framework.errorhandling.CAInvalidMessageException;
import com.ca.framework.errorhandling.InvalidClusterConfigException;

public interface MessagingIntegration {
	
	public String initMessagingSystem();
	public Future<RecordMetadata> publishMessage(Map message) throws CAInvalidMessageException, InvalidClusterConfigException;
	public void consumeMessage(String group, String topic, int threads);

}
