package com.ca.restapi;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ca.framework.communication.hose.TwilioSMSHose;
import com.ca.framework.context.TwilioContext;
import com.ca.framework.data.TwilioValueObject;
import com.ca.framework.errorhandling.CAException;
import com.ca.framework.integration.Messaging.KafkaIntegration;
//import org.apache.kafka.clients.producer;

@RestController
public class RestAPIController {
	KafkaIntegration kafka = new KafkaIntegration();

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/send/demo/wechat")
	public String publish(String msg, String phone) throws CAException {
//		kafka.initMessagingSystem();
//		// TODO: this object creation is not needed. Optimize this later
//		HashMap msgMap = new HashMap();
//		msgMap.put(Constants.TOPIC, Constants.KAFKA_DEMO_WECHAT);
//		msgMap.put(Constants.MESSAGE_VALUE, msg);
//		msgMap.put(Constants.MESSAGE_KEY, UUID.randomUUID()
//				.getMostSignificantBits());
//		try {
//			Future<RecordMetadata> rm = kafka.publishMessage(msgMap);
//		} catch (CAInvalidMessageException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidClusterConfigException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if (phone == null || phone.isEmpty()) {
			return "Param[phone] is missing or empty";
		}
		if (msg == null || msg.isEmpty()) {
			return "Param[msg] is missing or empty";
		}
		TwilioContext ctx = new TwilioContext("https://api.twilio.com/2010-04-01", 
				                              "AC589c5fe4aee0746a2285e718c0d8341e", 
				                              "7476f459573937aec71b04f768881b91",
				                              "+16789056928");
		TwilioValueObject tvo = new TwilioValueObject(ctx, phone, msg + "@" + new Date().toString());
		new TwilioSMSHose().execute(tvo);
		return msg;
	}

	@RequestMapping("/get/demo/wechat")
	public void consume(String group, String topic, int threads) {
		kafka.consumeMessage(group, topic, threads);
	}
	
	//@RequestMapping("/error")
	public String error() {
	  return "Sucks to be you!";
	}
}
