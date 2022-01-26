echo off
echo ####################
echo LONG BUILD Java Jars
echo ####################
echo on

set curdir=%cd%
cd %curdir%\..\MauxKafkaProducer
call mvn clean package

cd %curdir%\..\MauxKafkaConsumer
call mvn clean package

cd %curdir%