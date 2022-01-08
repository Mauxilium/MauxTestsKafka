# MauxTestsKafka
Java Spring project showing how to works with Kafka

This project is composed of four parts:
1. A Java Spring application which acts as a Producer
2. A Java Spring application which acts as a Consumer
3. A suite of Docker/Kubernetes scripts used to create a complete microservice test environment
4. A documents area where a list of results are published

**General View**
The Producer generates a list of messages with a payload containing an incremental integer value. The Consumer receives this stream of messages and log them.
This two agents could be multiplied and configured in order to work with differents Topics, Consumer Groups and Partitions.
By the way of a complete Elastic Stack (ELK +FileBeat) running in the same environment, it is possible to investigate the resulting behaviour of Kafka in a number of conditions.
