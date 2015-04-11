package com.chirpanywhere.framework.integration.Messaging;

import java.util.Map;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.RecordMetadata;

import com.chirpanywhere.framework.errorhandling.CAInvalidMessageException;

public interface MessagingIntegration {
	
	public String initMessagingSystem();
	public Future<RecordMetadata> publishMessage(Map message) throws CAInvalidMessageException;
	public Map consumeMessage(Channel ch);

}
