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
		while (it.hasNext()) {
			String message = new String(it.next().message());
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
			TwilioSMSSender sender = new TwilioSMSSender();
			try {
				sender.send(phone,msg);
			} catch (TwilioRestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Thread " + m_threadNumber + ": " + message);
		}
		System.out.println("Shutting down Thread: " + m_threadNumber);
	}
}
