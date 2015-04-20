#!/bin/bash
docker run -d -p=8080:8080 -v ${HOME}/git/runtime/ca-ingest/target:/usr/local/tomcat/webapps/chirp --name tomcat8 tomcat
docker-compose up -d
#docker-compose scale kafka=3
docker exec -t applicationlaunch_kafka_1 /opt/kafka_2.10-0.8.2.1/bin/kafka-topics.sh --zookeeper zk:2181 --topic kafka_demo_wechat --partitions 3 --replication-factor 2 --create
docker exec -t applicationlaunch_kafka_1 /opt/kafka_2.10-0.8.2.1/bin/kafka-topics.sh --zookeeper zk:2181 --topic kafka_demo_sms --partitions 3 --replication-factor 2 --create
docker exec -t applicationlaunch_kafka_1 /opt/kafka_2.10-0.8.2.1/bin/kafka-topics.sh --zookeeper zk:2181 --list
docker exec -t applicationlaunch_kafka_1 /opt/kafka_2.10-0.8.2.1/bin/kafka-topics.sh --zookeeper zk:2181 --topic kafka_demo_wechat --describe
docker exec -t applicationlaunch_kafka_1 /opt/kafka_2.10-0.8.2.1/bin/kafka-topics.sh --zookeeper zk:2181 --topic kafka_demo_sms --describe
