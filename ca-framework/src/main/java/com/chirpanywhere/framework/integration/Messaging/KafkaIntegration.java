package com.chirpanywhere.framework.integration.Messaging;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;

import com.chirpanywhere.framework.errorhandling.CAInvalidMessageException;
import com.chirpanywhere.framework.utils.Constants;
import kafka.producer.ProducerConfig;

public class KafkaIntegration implements MessagingIntegration {
	private ProducerConfig config = null;
	Properties props = null;
	KafkaProducer producer=null;

	@Override
	public String initMessagingSystem() {
		
		props = new Properties();
		props.put("bootstrap.servers", "kafka:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		

		props.put("producer.type", "async");
		props.put("request.required.acks", "1");
			
		producer = new KafkaProducer(props);

		return null;
	}

	@Override
	// The message will have the following in the map
	// Topic: The topic where to publish, key, message value
	// The partition will be found based on an algorithm. (Need an
	// implementation for round robbin)
	//
	public Future<RecordMetadata> publishMessage(Map message)
			throws CAInvalidMessageException {

		if (validateMessage(message) == false)
			throw new CAInvalidMessageException();

		List <PartitionInfo> partitions = producer.partitionsFor((String) message.get(Constants.TOPIC));
		
		int partitionId = pickRandomPartition(partitions);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ProducerRecord producerRec = new ProducerRecord(
				(String) message.get(Constants.TOPIC), partitionId,
				message.get(Constants.MESSAGE_KEY),
				message.get(Constants.MESSAGE_VALUE));

		Future<RecordMetadata> future = producer.send(producerRec);

		return future;
	}

	private int pickRandomPartition(List<PartitionInfo> partitions) {	
		Collections.shuffle(partitions);
		PartitionInfo part = partitions.get(0);
		return part.leader().id();
	}

	// To Do
	private boolean validateMessage(Map message) {
		return true;
	}

	@Override
	public Map consumeMessage(Channel ch) {
		// TODO Auto-generated method stub
		return null;
	}

}
