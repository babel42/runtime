package com.ca.framework.context;

import java.util.Map;
import java.util.Properties;
/*
 *Properties props = new Properties();
 * props.put("bootstrap.servers", "localhost:4242");
 * props.put("acks", "all");
 * props.put("retries", 0);
 * props.put("batch.size", 16384);
 * props.put("linger.ms", 1);
 * props.put("buffer.memory", 33554432);
 * props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
 * props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
 * 
*/
public class KafkaContext extends ContextData {
	
	private String topic;
	
	public KafkaContext(Map<String,Object> p, String t) {
		super(null, p);
		topic = t;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
