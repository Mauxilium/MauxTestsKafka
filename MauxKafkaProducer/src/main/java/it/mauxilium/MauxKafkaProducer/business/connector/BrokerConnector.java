package it.mauxilium.MauxKafkaProducer.business.connector;

import it.mauxilium.MauxKafkaProducer.business.model.PayloadToSend;

public interface BrokerConnector {
    void send(String topic, PayloadToSend payload);
}
