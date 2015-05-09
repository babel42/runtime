package com.ca.framework.factory.transaction;

import java.util.HashMap;

import com.ca.framework.data.IValueObject;
import com.ca.framework.errorhandling.CAException;
import com.ca.framework.factory.ICreatable;
import com.ca.framework.utils.Constants;
//import com.ca.framework.factory.IFactory;
//import com.ca.framework.factory.PersistenceFactory;
//import com.ca.framework.factory.ReceiveChannelFactory;
//import com.ca.framework.factory.SendChannelFactory;
//import com.ca.framework.factory.ValidatorFactory;
//import com.ca.framework.factory.AdInjectorFactory;



public class TransactionFactory {
	static TransactionFactory instance = null;
	// static ValidatorFactory validatorFact = null;
	// static SendChannelFactory sendChannelFact = null;
	// static ReceiveChannelFactory recvChannelFact = null;
	// static AdInjectorFactory adFactory = null;
	// static PersistenceFactory persistanceFact =null;

	IValueObject data = null;
	String intent;
	String transType;
	HashMap register;

	/**
	 * The constructor is only called by the getInstance method and the
	 * constructor is private.
	 * 
	 * @param vo
	 * @throws CAException
	 */
	private TransactionFactory(IValueObject vo) throws CAException {
		data = vo;
		intent = (String) vo.get(Constants.INTENT);
		transType = (String) vo.get(Constants.TRANSACTION_TYPE);
		if (!validate(data))
			throw new CAException(
					"VO did not have enough data for the factory to create an object",
					data);

		loadFactoryRegistrations();
	}

	private void loadFactoryRegistrations() {
		

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

	// ICreatable create() throws CAException
	// {
	// ICreatable created = create(transType,intent);
	// if (null == created)
	// {
	//
	// }
	//
	// return null;
	// }

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
	private ICreatable create(IValueObject vo) {
		ICreatable obj =  createObject(transType,intent);
		return obj;
	}
	
	private ICreatable createObject(String transType2,String intent2) {
		//String objectName = register.getClass(transType2,intent2);
		
		ICreatable obj; 
		//configueObject(obj,vo);
		return null;
	}
	
	private void configueObject(ICreatable obj, IValueObject vo) {		
	}

	private boolean validate(IValueObject vo) {
		// TODO Auto-generated method stub
		return false;
	}

	// public IFactory getAdInjectorFactory(IValueObject vo) {
	// adFactory.initialize(vo);
	// return adFactory;
	// }
	//
	// public IFactory getChannelSenderFactory(IValueObject vo) {
	// validatorFact.initialize(vo);
	// return validatorFact;
	// }
	//
	// public IFactory getChannelReceiverFactory(IValueObject vo) {
	// recvChannelFact.initialize(vo);
	// return recvChannelFact;
	// }
	//
	// public IFactory getPersistenceFactory(IValueObject vo) {
	// return null;
	// }
}