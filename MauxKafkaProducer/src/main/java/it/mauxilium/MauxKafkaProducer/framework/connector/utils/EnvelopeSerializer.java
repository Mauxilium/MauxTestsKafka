package it.mauxilium.MauxKafkaProducer.framework.connector.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.mauxilium.MauxKafkaProducer.framework.exception.KafkaSerializationException;
import it.mauxilium.MauxKafkaProducer.framework.model.KafkaEnvelope;

public class EnvelopeSerializer {

    public static String serialize(KafkaEnvelope envelope) throws KafkaSerializationException {
        if (envelope.getVers().isEmpty()) {
            throw new KafkaSerializationException("Please specify message Version", envelope.toString());
        }
        if (envelope.getName().isEmpty()) {
            throw new KafkaSerializationException("Please specify message class name", envelope.toString());
        }

        try {
            return new ObjectMapper().writeValueAsString(envelope);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new KafkaSerializationException(e.getMessage(), envelope.toString());
        }
    }
}
