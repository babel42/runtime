package com.ca.restapi;

import java.util.Date;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ca.framework.communication.hose.TwilioSMSHose;
import com.ca.framework.context.TwilioContext;
import com.ca.framework.data.IValueObject;
import com.ca.framework.data.TwilioValueObject;
import com.ca.framework.data.ValueObject;
import com.ca.framework.errorhandling.CAException;
import com.ca.framework.factory.ICreatable;
import com.ca.framework.factory.transaction.TransactionFactory;
import com.ca.framework.integration.Messaging.KafkaIntegration;
//import org.apache.kafka.clients.producer;
import com.ca.framework.utils.Constants;

@RestController
public class RestAPIController {
	KafkaIntegration kafka = new KafkaIntegration();

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	//STEPS: 
	// Transaction type will have following steps
	// Authorize: authorize
	// Audit: audit
	// Send Transaction: hose
	// Receive response: drain
	@RequestMapping("/send/demo/wechat")
	public String publish(String msg, String phone, String authToken)
			throws CAException {
		if (phone == null || phone.isEmpty()) {
			return "Param[phone] is missing or empty";
		}
		if (msg == null || msg.isEmpty()) {
			return "Param[msg] is missing or empty";
		}

		IValueObject vo = new ValueObject();

		vo.add(Constants.TWILIO_AUTH_TOKEN, authToken);
		vo.add(Constants.DEST_TEL_NUM, phone);
		vo.add(Constants.MESSAGE_VALUE, msg);

		populateContext(vo);

		TransactionFactory fact = TransactionFactory.getInstance(vo);
		ICreatable hose = fact.createObject(Constants.TRANSACTION_TYPE_SMS,
				"hose", vo);
		hose.configure(vo);

		IValueObject respVo = hose.execute(vo);

		// TwilioContext ctx = new
		// TwilioContext("https://api.twilio.com/2010-04-01",
		// "AC589c5fe4aee0746a2285e718c0d8341e",
		// "7476f459573937aec71b04f768881b91",
		// "+16789056928");
		// TwilioValueObject tvo = new TwilioValueObject(ctx, phone, msg + "@" +
		// new Date().toString());
		// new TwilioSMSHose().execute(tvo);
		return msg;
	}

	/**
	 * This will be the elements in user profile etc. It is stored as
	 * Constants.DYNAMIC_CREATABLE_CONFIG_DATA tag in the vo. TODO: Make call to
	 * Puneet's API here to get dynamic context of the call by passing it the
	 * authentication key. Other static context is already loaded into the
	 * creatable by the creatableObj.configure() method, from resource bundles,
	 * as the new object configure itself.This will be in the
	 * Constants.DYNAMIC_CREATABLE_CONFIG_DATA tag in the vo
	 * 
	 * @param vo
	 *            It is the new ValueObject that carries all the information for
	 *            the transaction as it moves through its workflow steps. It
	 *            will be populated with the dynamic context in this call
	 */
	private void populateContext(IValueObject vo) {
		// This will be used as HashMap staticCxt = (HashMap)
		// vo.get(Constants.STATIC_CREATABLE_CONFIG_DATA);

	}

	@RequestMapping("/get/demo/wechat")
	public void consume(String group, String topic, int threads) {
		kafka.consumeMessage(group, topic, threads);
	}

	// @RequestMapping("/error")
	public String error() {
		return "Sucks to be you!";
	}
}
