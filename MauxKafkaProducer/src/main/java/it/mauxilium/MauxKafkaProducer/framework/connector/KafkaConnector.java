package it.mauxilium.MauxKafkaProducer.framework.connector;

import it.mauxilium.MauxKafkaProducer.adapter.connector.BrokerConnectorAdapter;
import it.mauxilium.MauxKafkaProducer.business.model.PayloadToSend;
import it.mauxilium.MauxKafkaProducer.framework.connector.utils.KafkaEnvelopeBuilder;
import it.mauxilium.MauxKafkaProducer.framework.exception.KafkaSerializationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Component
public class KafkaConnector implements BrokerConnectorAdapter {

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public boolean send(String topic, PayloadToSend payload) {
        log.debug("ASSUMED TRANSACTION ID PREFIX: {}", template.getTransactionIdPrefix());

        try {
            ListenableFuture<SendResult<String, String>> response = template.send(topic, serializePayload(payload));
        } catch (Exception ex) {
            log.error("Failure sending to topic: {}", topic, ex);
            return false;
        }

        return true;
    }

    private String serializePayload(PayloadToSend payload) {
        String serializedPayload;
        try {
            serializedPayload = KafkaEnvelopeBuilder.serializeEnvelopeOf(payload);
        } catch (KafkaSerializationException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }

        return serializedPayload;
    }
}
