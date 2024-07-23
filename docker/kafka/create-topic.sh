#!/bin/bash
TOPIC_NAME=settlements
CONTAINER_ID=c394f2348ed7
BOOTSTRAP_SERVER=localhost:9092
KAFKA_CREATE_CMD="kafka-topics --bootstrap-server $BOOTSTRAP_SERVER --topic $TOPIC_NAME --create --partitions 3 --replication-factor 1"
echo "EXECUTE COMMAND --> docker exec -it ${CONTAINER_ID} ${KAFKA_CREATE_CMD}"
docker exec -it ${CONTAINER_ID} ${KAFKA_CREATE_CMD}
