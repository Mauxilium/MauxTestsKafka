FROM openjdk:11
#FROM openjdk:11-jre-slim@sha256:31a5d3fa2942eea891cf954f7d07359e09cf1b1f3d35fb32fedebb1e3399fc9e
CMD mkdir /usr/src/myapp
WORKDIR /usr/src/myapp
COPY ./target/MauxKafkaConsumer-0.0.1-SNAPSHOT.jar /usr/src/myapp
CMD ["java", "-Dspring.profiles.active=docker", "-jar", "MauxKafkaConsumer-0.0.1-SNAPSHOT.jar"]

