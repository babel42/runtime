package com.ca.framework.factory.transaction;


import com.ca.framework.data.IValueObject;
import com.ca.framework.errorhandling.CAException;
import com.ca.framework.factory.ICreatable;
import com.ca.framework.factory.IFactory;
import com.ca.framework.factory.PersistenceFactory;
import com.ca.framework.factory.ReceiveChannelFactory;
import com.ca.framework.factory.SendChannelFactory;
import com.ca.framework.factory.ValidatorFactory;
import com.ca.framework.utils.Constants;
import com.ca.framework.factory.AdInjectorFactory;

public class TransactionFactory {
        static TransactionFactory instance = null;
        static ValidatorFactory validatorFact = null;
        static SendChannelFactory sendChannelFact = null;
        static ReceiveChannelFactory recvChannelFact = null;
        static AdInjectorFactory adFactory = null;
        static PersistenceFactory persistanceFact =null;
        
        IValueObject data=null;
        String intent;
        String transType;


        private TransactionFactory(IValueObject vo) throws CAException {
        	data =vo;
        	intent = (String) vo.get(Constants.INTENT);
        	transType = (String) vo.get(Constants.TRANSACTION_TYPE);
        	if (!validate(data))
        		throw new CAException("VO did not have enough data for the factory to create an object",data);
        	
        	loadFactoryRegistrations();
        }

        private void loadFactoryRegistrations() {
			
			
		}

		public static TransactionFactory getInstance(IValueObject vo) throws CAException {
                synchronized (instance) {
                        if (null == instance)
                                instance = new TransactionFactory(vo);
                }
                return instance;
        }
        
        ICreatable create() throws CAException
        {	
        	ICreatable created = create(transType,intent);
        	if (null == created)
        	{
        		
        	}
        	
        	return null;
        }

        private ICreatable create(String transType, String intent) {
			
			return null;
		}

		private boolean validate(IValueObject vo) {
			// TODO Auto-generated method stub
			return false;
		}

		public IFactory getAdInjectorFactory(IValueObject vo) {
                adFactory.initialize(vo);
                return adFactory;
        }

        public IFactory getChannelSenderFactory(IValueObject vo) {
                validatorFact.initialize(vo);
                return validatorFact;
        }

        public IFactory getChannelReceiverFactory(IValueObject vo) {
                recvChannelFact.initialize(vo);
                return recvChannelFact;
        }

        public IFactory getPersistenceFactory(IValueObject vo) {
                return null;
        }
}