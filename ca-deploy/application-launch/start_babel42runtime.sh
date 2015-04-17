#!/bin/bash
docker-compose up -d
docker-compose scale kafka=3
docker exec -t dockercompose_kafka_1 /opt/kafka_2.10-0.8.2.1/bin/kafka-topics.sh --zookeeper zk:2181 --topic kafka_demo_wechat --partitions 3 --replication-factor 2 --create
docker exec -t dockercompose_kafka_1 /opt/kafka_2.10-0.8.2.1/bin/kafka-topics.sh --zookeeper zk:2181 --topic kafka_demo_sms --partitions 3 --replication-factor 2 --create
docker exec -t dockercompose_kafka_1 /opt/kafka_2.10-0.8.2.1/bin/kafka-topics.sh --zookeeper zk:2181 --list
docker exec -t dockercompose_kafka_1 /opt/kafka_2.10-0.8.2.1/bin/kafka-topics.sh --zookeeper zk:2181 --topic kafka_demo_wechat --describe
docker exec -t dockercompose_kafka_1 /opt/kafka_2.10-0.8.2.1/bin/kafka-topics.sh --zookeeper zk:2181 --topic kafka_demo_sms --describe
docker run -p "8080:8080" --link="dockercompose_kafka_1:kafka" --link="dockercompose_zookeeper_1:zk" -t babel42/runtime:v2 
