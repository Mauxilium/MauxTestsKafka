package it.mauxilium.MauxKafkaProducer.framework.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.mauxilium.MauxKafkaProducer.framework.exception.KafkaSerializationException;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TopicItem {

    public String buildPayload() throws KafkaSerializationException {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new KafkaSerializationException(e.getMessage(), this.toString());
        }
    }

}
