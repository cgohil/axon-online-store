#!/usr/bin/env bash
docker-compose --file docker-compose-kafka-cluster.yml exec -T broker1 kafka-topics --create --zookeeper \
zookeeper:2181 --replication-factor 2 --partitions 2 --topic domain-events-topic