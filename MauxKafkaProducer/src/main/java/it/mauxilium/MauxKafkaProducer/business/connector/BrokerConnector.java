package it.mauxilium.MauxKafkaProducer.business.connector;

import it.mauxilium.MauxKafkaProducer.business.model.MessageModel;

public interface BrokerConnector {
    void send(String topic, MessageModel payload);
}
