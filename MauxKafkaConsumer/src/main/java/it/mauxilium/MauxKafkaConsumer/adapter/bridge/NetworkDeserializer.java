package it.mauxilium.MauxKafkaConsumer.adapter.bridge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.mauxilium.MauxKafkaConsumer.adapter.exception.InvalidMessageReceivedException;
import it.mauxilium.MauxKafkaConsumer.adapter.model.MessageOnNetworkModel;

import java.util.function.Function;

public class NetworkDeserializer {

    public static Function<String, MessageOnNetworkModel> deserialize = msg -> {
        try {
            return (new ObjectMapper()).readValue(msg, MessageOnNetworkModel.class);
        } catch (JsonProcessingException ex) {
            throw new InvalidMessageReceivedException(msg);
        }
    };
}
