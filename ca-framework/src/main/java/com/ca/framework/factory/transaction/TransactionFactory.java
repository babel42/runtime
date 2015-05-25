package com.ca.framework.factory.transaction;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.ca.framework.data.IValueObject;
import com.ca.framework.errorhandling.CAException;
import com.ca.framework.factory.ICreatable;
import com.ca.framework.utils.Constants;

public class TransactionFactory {
	static TransactionFactory instance = null;

	IValueObject data = null;
	HashMap<String, HashMap> register;

	/**
	 * The constructor is only called by the getInstance method and the
	 * constructor is private.
	 * 
	 * @param vo
	 * @throws CAException
	 */
	private TransactionFactory(IValueObject vo) throws CAException {
		loadFactoryRegistrations();
	}

	private void loadFactoryRegistrations() {
		// Add more registrations here. Thing to remember is to to create the
		// resource file in the classpath.
		// If needed, this file can be externalize in the file system, but I
		// have
		// decided against that as that will make the deployment fickle

		// Context will register all static context for all types of
		// transactions and customers
		//register("context.properties", "context");

		// Following will register all the Creatables needed for each intent
		register("hose.properties", "hose");
		register("drain.properties","drain");
	}

	private void register(String bundleFile, String intent) {
		// Will require registerType.properties file in the classpath
		ResourceBundle bundle = ResourceBundle.getBundle(bundleFile);
		Enumeration<String> keys = bundle.getKeys();
		String key = null;
		Object obj = null;
		HashMap<String, Object> classCategoryRegister = new HashMap();

		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			String className = (String) bundle.getObject(key);
			obj = null;
			try {
				obj = Class.forName(className);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			classCategoryRegister.put(key, obj);
		}
		register.put(intent, classCategoryRegister);
	}

	/**
	 * This method hands out the factory object that creates all other objects
	 * in the system. As we do not want to load the registration data every time
	 * this is invoked, the factory object is a singleton. The VO should contain
	 * all the runtime information needed to create and configure the object
	 * that is created. If the VO object is not populated with the correct data,
	 * the transaction flow will throw the CAException.
	 * 
	 * @param vo
	 * @return
	 * @throws CAException
	 */
	public static TransactionFactory getInstance(IValueObject vo)
			throws CAException {
		synchronized (instance) {
			if (null == instance)
				instance = new TransactionFactory(vo);
		}
		return instance;
	}

	/**
	 * Creates an object based on the transaction type and the intent. This is
	 * used by clients like controllers that control the flow of the transaction
	 * 
	 * @param vo
	 *            The valueobject must contain intent and transType data.
	 *            Transtype variable defines the actual transaction type that is
	 *            being handled by the object (e.g. email/SMS etc). The intent
	 *            variable defines what is the intended functionality for the
	 *            required object
	 * @return the createable object for
	 */
	public ICreatable create(IValueObject vo) {
		String transType = (String) vo.get(Constants.TRANSACTION_TYPE);
		String intent = (String) vo.get(Constants.INTENT);
		ICreatable obj = createObject(transType, intent, vo);
		return obj;
	}

	/**
	 * Creates an object based on the transaction type and the intent. This is
	 * used by clients like controllers that control the flow of the transaction
	 * 
	 * @param transType
	 *            This variable defines the actual transaction type that is
	 *            being handled by the object (e.g. email/SMS etc)
	 * @param intent
	 *            This variable defines what is the intended functionality for
	 *            the required object
	 * @return the createable object for
	 */
	public ICreatable createObject(String transType, String intent,
			IValueObject vo) {
		HashMap intentMap = (HashMap) register.get(intent);
		ICreatable obj = (ICreatable) intentMap.get(transType);

		// Object will configure itself from the data in the VO. After
		// configuration, it will be ready to execute
		// TODO: Check if the same factory object can be given to every caller
		// or does it need to be cloned? (i.e. will every thread be able to use
		// the same object
		obj.configure(vo);
		return obj;
	}

	private boolean validate(IValueObject vo) {
		// TODO Auto-generated method stub
		return false;
	}
}