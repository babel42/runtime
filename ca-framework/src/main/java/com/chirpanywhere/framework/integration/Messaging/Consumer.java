package com.chirpanywhere.framework.integration.Messaging;

import java.util.StringTokenizer;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import com.chirpanywhere.outgoing.twilio.TwilioSMSSender;
import com.twilio.sdk.TwilioRestException;

public class Consumer implements Runnable {
	private KafkaStream m_stream;
	private int m_threadNumber;

	public Consumer(KafkaStream a_stream, int a_threadNumber) {
		m_threadNumber = a_threadNumber;
		m_stream = a_stream;
	}

	public void run() {
		ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
		System.out.println("Consumer["+ m_threadNumber+"].run() begin");
		System.out.println("Consumer["+ m_threadNumber+"].run() got messages:" + it.size());
		System.out.println("Consumer["+ m_threadNumber+"].run() it.hasNext:" + it.hasNext());
		System.out.println("Consumer["+ m_threadNumber+"].run() it.length:" + it.length());
		System.out.println("Consumer["+ m_threadNumber+"].run() it.counted:" + it.counted());
        try {
		System.out.println("Consumer["+ m_threadNumber+"].run() it.next:" + it.next());
        } catch(Throwable t) {
        	System.out.println(t.getMessage());
        	t.printStackTrace();
        }

		while(it.hasNext()) {
			
			String message = new String(it.next().message());
			System.out.println("Consumer["+ m_threadNumber+"].run() message:" + message);
			// Message: phone:<number>,key:uuid,msg:<message>
			StringTokenizer st = new StringTokenizer(message);
			String phoneText = null;
			String keyText = null;
			String msgText = null;

			if (st != null) {
				phoneText = st.nextToken(",");
				keyText = st.nextToken(",");
				msgText = st.nextToken(",");
			}
			String phone = null;
			if (phoneText !=null)
			{
				StringTokenizer pst = new StringTokenizer(phoneText);
				phone = pst.nextToken(":");
				phone = pst.nextToken(":");
			}
			
			String msg = null;
			System.out.println("Consumer["+ m_threadNumber+"].run() sending to TwilioAPI");

			TwilioSMSSender sender = new TwilioSMSSender();
			try {
				sender.send(phone,msg);
			} catch (TwilioRestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("Consumer["+ m_threadNumber+"].run(): shutting down");
	}
}
