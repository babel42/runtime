package com.chirpanywhere.framework.integration.Messaging;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Future;

import kafka.producer.ProducerConfig;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;

import com.chirpanywhere.framework.errorhandling.CAInvalidMessageException;
import com.chirpanywhere.framework.errorhandling.InvalidClusterConfigException;
import com.chirpanywhere.framework.utils.Constants;

public class KafkaIntegration implements MessagingIntegration {
	private ProducerConfig config = null;
	Properties props = null;
	KafkaProducer producer = null;
	
	
	public String initMessagingSystem() {

		props = new Properties();
		props.put("bootstrap.servers", Constants.KAFKA_HOST);
		// props.put("serializer.class", "kafka.serializer.StringEncoder");
		//props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		//props.put("producer.type", "sync");
		props.put("acks", "0");

		producer = new KafkaProducer(props);

		return null;
	}

	// The message will have the following in the map
	// Topic: The topic where to publish, key, message value
	// The partition will be found based on an algorithm. (Need an
	// implementation for round robbin)
	//
	public Future<RecordMetadata> publishMessage(Map message)
			throws CAInvalidMessageException, InvalidClusterConfigException {

		if (validateMessage(message) == false)
		{
			String text = "The message is in an invalid format";
			System.out.println("text");
			throw new CAInvalidMessageException(text);
		}
			

		List<PartitionInfo> partitions = producer
				.partitionsFor((String) message.get(Constants.TOPIC));

		int partitionId = pickRandomPartition(partitions);
		
       String msg = (String)message.get(Constants.MESSAGE_VALUE);
       String topic = (String) message.get(Constants.TOPIC);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ProducerRecord producerRec = new ProducerRecord(
				topic, partitionId,
				message.get(Constants.MESSAGE_KEY),
				msg);
System.out.println("KafkaIntegration.publishMessage:topic/message[" + topic + "/" +  msg + "]");
		Future<RecordMetadata> future = producer.send(producerRec);

		return future;
	}

	private int pickRandomPartition(List<PartitionInfo> partitions) throws InvalidClusterConfigException {
		int size = partitions.size();
		
		if (size == 0)
		{			
			System.out.println("The cluster configuration does not have any nodes in it");
			throw new InvalidClusterConfigException("The cluster configuration does not have any nodes in it");		
		}

		Random rand = new Random();
		int part =  rand.nextInt(size);
		
		//-1, because it is going to be used as index for on a list
		//@TODO we don't know why we need this....
		if (part<=0) return 0;
		
		return part-1;
	}

	// To Do
	private boolean validateMessage(Map message) {
		return true;
	}

	public void consumeMessage(String group, String topic,int threads) {
		SimpleExample ex = new SimpleExample();
		List<String> list = new Vector();
		list.add(Constants.ZOOKEEPER_HOST);
		try {
			ex.run(1, Constants.TOPIC, 0, list, Integer.parseInt(Constants.ZOOKEEPER_PORT));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*ConsumerGroup example = new ConsumerGroup("zk:2181", group, topic);
        example.execute(threads);*/
        /*
        System.out.print("KafkaIntegration.consumeMessage: just did example.run(), about to wait 3 seconds...");
        try{Thread.sleep(3000);}catch(Exception e) {System.out.println("Cannot consume msg. Ex:" + e.getMessage());}
        System.out.println("KafkaIntegration.consumeMessage: About to example.shutdown()");
        example.shutdown();
        System.out.println("KafkaIntegration.consumeMessage: shutdown() complete");
        */
	}
}
