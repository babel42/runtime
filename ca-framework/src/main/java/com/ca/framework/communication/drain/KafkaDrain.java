package com.ca.framework.communication.drain;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;

import com.ca.framework.context.KafkaContext;
import com.ca.framework.data.IValueObject;
import com.ca.framework.errorhandling.CAException;
import com.ca.framework.errorhandling.InvalidClusterConfigException;
import com.ca.framework.factory.ICreatable;

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
		Map<String, Object> props = ctx.getMap();
		KafkaProducer producer = new KafkaProducer(props);
		String topic = ctx.getTopic();
		
		List<PartitionInfo> partitions = producer.partitionsFor(topic);
		int partitionId;
		try {
			partitionId = pickRandomPartition(partitions);
		} catch (InvalidClusterConfigException e) {
			throw new CAException(e, e.getLocalizedMessage(), 1025);
		}
        String msgKey = (String) vo.get("KAFKA_MESSAGE_KEY");
        String msg = (String) vo.get("MESSAGE");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ProducerRecord producerRec = new ProducerRecord(topic, partitionId,
				msgKey, msg);
		
		Future<RecordMetadata> future = producer.send(producerRec);
		vo.add("KAFKA.RETURN.FUTURE", future);
		return vo;
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