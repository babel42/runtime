package com.ca.framework.communication.drain;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;

import com.ca.framework.data.IValueObject;
import com.ca.framework.errorhandling.CAException;
import com.ca.framework.errorhandling.InvalidClusterConfigException;
import com.ca.framework.factory.ICreatable;
import com.ca.framework.utils.Constants;

/**
 * This class will receive/drain messages from some external source. The
 * AbstractFactory is responsible for initializing all Kafka producer client
 * data, and all we're doing in here, is to accept a single message.
 * 
 * @author keyur
 *
 */
public class KafkaDrain implements ICreatable {

	public IValueObject execute(IValueObject vo) throws CAException {

		KafkaContext ctx = (KafkaContext) vo.getContext();
		
		List<PartitionInfo> partitions = producer
				.partitionsFor((String) message.get(Constants.TOPIC));

		int partitionId = pickRandomPartition(partitions);

		String msg = (String) message.get(Constants.MESSAGE_VALUE);
		String topic = (String) message.get(Constants.TOPIC);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ProducerRecord producerRec = new ProducerRecord(topic, partitionId,
				message.get(Constants.MESSAGE_KEY), msg);
		System.out.println("KafkaIntegration.publishMessage:topic/message["
				+ topic + "/" + msg + "]");
		Future<RecordMetadata> future = producer.send(producerRec);

		return future;
	}

	private int pickRandomPartition(List<PartitionInfo> partitions)
			throws InvalidClusterConfigException {
		int size = partitions.size();

		if (size == 0) {
			System.out
					.println("The cluster configuration does not have any nodes in it");
			throw new InvalidClusterConfigException(
					"The cluster configuration does not have any nodes in it");
		}

		Random rand = new Random();
		int part = rand.nextInt(size);

		// -1, because it is going to be used as index for on a list
		// @TODO we don't know why we need this....
		if (part <= 0)
			return 0;

		return part - 1;
	}
}