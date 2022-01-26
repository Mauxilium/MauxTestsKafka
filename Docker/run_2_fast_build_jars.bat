echo off
echo ###############
echo BUILD Java Jars
echo ###############
echo on

set curdir=%cd%
cd %curdir%\..\MauxKafkaProducer
call mvn clean package -DskipTests

cd %curdir%\..\MauxKafkaConsumer
call mvn clean package -DskipTests

cd %curdir%

