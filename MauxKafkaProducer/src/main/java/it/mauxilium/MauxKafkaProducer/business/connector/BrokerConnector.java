package it.mauxilium.MauxKafkaProducer.business.connector;

import it.mauxilium.MauxKafkaProducer.business.model.PayloadToSend;

public interface BrokerConnector {
    boolean send(String topic, PayloadToSend payload);
}
