echo off
echo ###################
echo Build DOCKER Images
echo ###################
echo on

set curdir=%cd%
cd %curdir%\..\MauxKafkaProducer
call docker build -t mauxkproducer:1.0.0 .

cd %curdir%\..\MauxKafkaConsumer
docker build -t mauxkconsumer:1.0.0 .

cd %curdir%
