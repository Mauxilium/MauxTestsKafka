package it.mauxilium.MauxKafkaProducer.framework.connector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.mauxilium.MauxKafkaProducer.adapter.connector.BrokerConnectorAdapter;
import it.mauxilium.MauxKafkaProducer.business.model.MessageToSend;
import it.mauxilium.MauxKafkaProducer.framework.exception.KafkaSerializationException;
import it.mauxilium.MauxKafkaProducer.framework.exception.SendSampleException;
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
    public void send(String topic, MessageToSend payload) {
        try {
            log.debug("Send to topic {} this payload: {}", topic, payload);
            ListenableFuture<SendResult<String, String>> response = template.send(topic, serializePayload(payload));
            log.debug("Topic {} accepts payload.", topic);
        } catch (Exception ex) {
            log.error("Failure sending to topic: {}", topic, ex);
            throw new SendSampleException(
                    payload.getItemIndex(),
                    payload.getHowToSend(),
                    payload.getSessionId(),
                    topic,
                    ex.toString());
        }
    }

    private String serializePayload(MessageToSend payload) {
        try {
            return new ObjectMapper().writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new KafkaSerializationException(e.getMessage(), payload.toString());
        }
    }
}
