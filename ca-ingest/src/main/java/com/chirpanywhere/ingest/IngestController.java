package com.chirpanywhere.ingest;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Future;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chirpanywhere.framework.errorhandling.CAInvalidMessageException;
import com.chirpanywhere.framework.errorhandling.InvalidClusterConfigException;
import com.chirpanywhere.framework.integration.Messaging.KafkaIntegration;
import com.chirpanywhere.framework.utils.Constants;




//import org.apache.kafka.clients.producer;
import org.apache.kafka.clients.producer.RecordMetadata;

@RestController
@RequestMapping("/demo")
public class IngestController {
	KafkaIntegration kafka = new KafkaIntegration();

	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping(value="/ott/wechat/send",method=RequestMethod.POST)
	public String publish(@RequestBody String msg, @RequestParam("phone") String phone) {
		System.out.println("Producer: Phone[" + phone + "], msg[" + msg+"]");
		String uuid = UUID.randomUUID().toString();
		kafka.initMessagingSystem();
		// TODO: this object creation is not needed. Optimize this later
		HashMap msgMap = new HashMap();
		msgMap.put(Constants.TOPIC, Constants.KAFKA_DEMO_WECHAT);
		//msgMap.put(Constants.MESSAGE_VALUE, "phone:"+phone+",key:" + uuid + ",msg:" + msg);
		msgMap.put(Constants.MESSAGE_VALUE, "phone:"+phone+",key:" + uuid + ",msg:" + msg);
		msgMap.put(Constants.MESSAGE_KEY, uuid);
		msgMap.put(Constants.PAYLOAD, msg);
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

	@RequestMapping(value="/ott/wechat/get",method=RequestMethod.GET)
	public void consume(@RequestParam("group") String group, 
			@RequestParam("topic") String topic, @RequestParam("numOfThreads") int numOfThreads) {
		kafka.consumeMessage(group, topic, numOfThreads);
		return;
	}
}
