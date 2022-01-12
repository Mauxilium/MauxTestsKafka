package it.mauxilium.MauxKafkaProducer.framework.connector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.mauxilium.MauxKafkaProducer.adapter.connector.BrokerConnectorAdapter;
import it.mauxilium.MauxKafkaProducer.business.model.MessageModel;
import it.mauxilium.MauxKafkaProducer.framework.exception.KafkaSerializationException;
import it.mauxilium.MauxKafkaProducer.framework.exception.SendSampleException;
import it.mauxilium.MauxKafkaProducer.framework.mapper.MessageMapper;
import it.mauxilium.MauxKafkaProducer.framework.model.MessageOnNetwork;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Value
@AllArgsConstructor
public class KafkaConnector implements BrokerConnectorAdapter {

    private static final String KIBANA_PRODUCER_LOG = "PRODUCED: {}";

    KafkaTemplate<String, String> template;

    @Override
    public void send(String topic, MessageModel payload) {
        try {
            log.debug("Send to topic {} this payload: {}", topic, payload);
            MessageOnNetwork msgToSend = MessageMapper.internalToExternalMapper.apply(payload);
            log.info(KIBANA_PRODUCER_LOG, msgToSend); // This log si used by Kibana in order to populate his dashboard
            ListenableFuture<SendResult<String, String>> response = template.send(topic, serializePayload(msgToSend));
            log.debug("Topic {} accepts payload.", topic);
        } catch (Exception ex) {
            log.error("Failure sending to topic: {}; {}", topic, ex, ex.getCause());
            throw new SendSampleException(
                    payload.getItemIndex(),
                    payload.getHowToSend(),
                    payload.getSessionId(),
                    topic,
                    ex.getLocalizedMessage() + "; " + ex.getCause());
        }
    }

    private String serializePayload(MessageOnNetwork payload) {
        try {
            return new ObjectMapper().writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new KafkaSerializationException(e.getMessage(), payload.toString());
        }
    }
}
