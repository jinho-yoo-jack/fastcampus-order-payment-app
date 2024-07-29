#!/bin/bash
ZK=docker-zookeeper-1
BROKER=docker-kafka-1-1
REGISTRY=docker-schema-registry-1
docker stop $ZK && docker rm $ZK
docker stop $BROKER && docker rm $BROKER
docker stop $REGISTRY && docker rm $REGISTRY
