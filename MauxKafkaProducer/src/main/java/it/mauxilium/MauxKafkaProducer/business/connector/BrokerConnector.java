package it.mauxilium.MauxKafkaProducer.business.connector;

import it.mauxilium.MauxKafkaProducer.business.model.MessageToSend;

public interface BrokerConnector {
    void send(String topic, MessageToSend payload);
}
