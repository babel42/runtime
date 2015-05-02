package com.ca.framework.context;

import org.apache.kafka.clients.producer.KafkaProducer;

import com.ca.framework.data.ValueObject;


public class KafkaContext extends ContextData {
	private KafkaProducer producer;
	private String topic;
	
	public KafkaContext(KafkaProducer p, String t) {
		super(null);
		producer = p;
		topic = t;
	}

	public KafkaProducer getProducer() {
		return producer;
	}

	public void setProducer(KafkaProducer producer) {
		this.producer = producer;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
