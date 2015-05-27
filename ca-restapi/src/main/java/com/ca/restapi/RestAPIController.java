package com.ca.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ca.framework.data.IValueObject;
import com.ca.framework.data.TwilioValueObject;
import com.ca.framework.data.ValueObject;
import com.ca.framework.errorhandling.CAException;
import com.ca.framework.factory.ICreatable;
import com.ca.framework.factory.transaction.TransactionFactory;
import com.ca.framework.utils.Constants;

@RestController
public class RestAPIController {
	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	// STEPS:
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
		// Establish transaction type and initial intent
		vo.add(Constants.TRANSACTION_TYPE, Constants.SMS_TRANSACTION);
		vo.add(Constants.INTENT, Constants.TWILIO_HOSE);
		
		//Populate data needed by creatable
		populateDynamicContext(vo);
		vo.add(Constants.TWILIO_AUTH_TOKEN, authToken);
		vo.add(Constants.DEST_TEL_NUM, phone);
		vo.add(Constants.MESSAGE_VALUE, msg);
		TwilioValueObject tVo = new TwilioValueObject(vo);
		vo.add(Constants.TWILIO_VO, tVo);

		// Execute it
		IValueObject respVo = createAndExecute(vo);

		// change the following return to return something useful to the client
		// e.g. coorelation id
		return "DUMMY";
	}

	/**
	 * This is a common method to execute all calls coming into the system
	 * 
	 * @param vo
	 *            contains context and data for transaction execution
	 * @return value object to the caller and caller can return any value out of
	 *         the VO
	 * @throws CAException
	 */
	private IValueObject createAndExecute(IValueObject vo) throws CAException {
		TransactionFactory fact = TransactionFactory.getInstance(vo);
		ICreatable obj = fact.createObject(
				(String) vo.get(Constants.TRANSACTION_TYPE),
				(String) vo.get(Constants.INTENT), vo);

		// ToDo:See if this needs to be done in factory or here. Decision point
		// is in the comments in the factory
		// Factory is also configuring the creatable for now. Need to remove
		// from one of these 2 places
		obj.configure(vo);
		IValueObject respVo = obj.execute(vo);
		return vo;

	}

	/**
	 * This will be the elements in user profile etc. It is stored as
	 * Constants.DYNAMIC_CREATABLE_CONFIG_DATA tag in the vo. TODO: Make call to
	 * Puneet's API here to get dynamic context of the call by passing it the
	 * user authentication key. Other static context is already loaded into the
	 * creatable by factory, from resource bundle.Static context will be in the
	 * Constants.STATIC_CREATABLE_CONFIG_DATA tag in the vo
	 * 
	 * @param vo
	 *            It is the new ValueObject that carries all the information for
	 *            the transaction as it moves through its workflow steps. It
	 *            will be populated with the dynamic context in this call
	 */
	private void populateDynamicContext(IValueObject vo) {
		// This will be used as HashMap staticCxt = (HashMap)
		// vo.get(Constants.DYNAMIC_CREATABLE_CONFIG_DATA);
	}

	@RequestMapping("/get/demo/wechat")
	public void consume(String group, String topic, int threads) {
	}

	// @RequestMapping("/error")
	public String error() {
		return "Sucks to be you!";
	}
}
