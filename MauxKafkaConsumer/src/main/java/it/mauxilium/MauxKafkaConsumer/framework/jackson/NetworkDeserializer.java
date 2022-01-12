package it.mauxilium.MauxKafkaConsumer.framework.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.mauxilium.MauxKafkaConsumer.adapter.exception.InvalidMessageReceivedException;
import it.mauxilium.MauxKafkaConsumer.framework.model.MessageOnNetwork;

import java.util.function.Function;

public class NetworkDeserializer {

    public static Function<String, MessageOnNetwork> deserialize = msg -> {
        try {
            return (new ObjectMapper()).readValue(msg, MessageOnNetwork.class);
        } catch (JsonProcessingException ex) {
            throw new InvalidMessageReceivedException(msg, ex);
        }
    };
}
