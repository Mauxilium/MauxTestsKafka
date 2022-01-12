#! /bin/sh
cd ../MauxKafkaProducer
mvn clean package -DskipTests
cd ../MauxKafkaConsumer
mvn clean package -DskipTests
cd ..

