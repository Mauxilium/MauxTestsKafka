package it.mauxilium.MauxKafkaProducer.framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.mauxilium.MauxKafkaProducer.framework.model.KafkaEnvelope;
import it.mauxilium.MauxKafkaProducer.framework.model.TopicItem;

public abstract class EnvelopeDeserializer {

    public abstract Class getPayloadClass(KafkaEnvelope envelope);

    public TopicItem deserialize(String buffer) {
        KafkaEnvelope envelope = null;
        try {
            envelope = new ObjectMapper().readValue(buffer, KafkaEnvelope.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        TopicItem obj = null;
        try {
            obj = (TopicItem) new ObjectMapper().readValue(envelope.getPayload(), getPayloadClass(envelope));
        } catch (Exception e) {
            e.printStackTrace();
            return new TopicItem();
        }
        return obj;
    }
}
