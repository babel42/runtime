package com.chirpanywhere.ingest;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Future;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chirpanywhere.framework.errorhandling.CAInvalidMessageException;
import com.chirpanywhere.framework.errorhandling.InvalidClusterConfigException;
import com.chirpanywhere.framework.integration.Messaging.KafkaIntegration;
import com.chirpanywhere.framework.utils.Constants;
//import org.apache.kafka.clients.producer;
import org.apache.kafka.clients.producer.RecordMetadata;

@RestController
public class IngestController {
	KafkaIntegration kafka = new KafkaIntegration();

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/send/demo/wechat")
	public String publish(String msg) {
		kafka.initMessagingSystem();
		// TODO: this object creation is not needed. Optimize this later
		HashMap msgMap = new HashMap();
		msgMap.put(Constants.TOPIC, Constants.KAFKA_DEMO_WECHAT);
		msgMap.put(Constants.MESSAGE_VALUE, msg);
		msgMap.put(Constants.MESSAGE_KEY, UUID.randomUUID()
				.toString());
		try {
			Future<RecordMetadata> rm = kafka.publishMessage(msgMap);
		} catch (CAInvalidMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidClusterConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/get/demo/wechat")
	public void consume(String group, String topic, int threads) {
		kafka.consumeMessage(group, topic, threads);
	}
}
