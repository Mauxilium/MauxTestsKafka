#! /bin/sh
cd ../MauxKafkaProducer
docker build -t mauxkproducer:1.0.0 .
cd ../MauxKafkaConsumer
docker build -t mauxkconsumer:1.0.0 .
cd ..

