package com.ca.framework.context;

import org.apache.kafka.clients.producer.KafkaProducer;

import com.ca.framework.data.ValueObject;


public class KafkaContext extends ContextData {
	private KafkaProducer producer;
	
	public KafkaContext(KafkaProducer p) {
		super(null);
		producer = p;
	}

	public KafkaProducer getProducer() {
		return producer;
	}

	public void setProducer(KafkaProducer producer) {
		this.producer = producer;
	}
	
	
  
}
