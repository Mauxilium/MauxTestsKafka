package it.mauxilium.MauxKafkaProducer.framework.model;

import it.mauxilium.MauxKafkaProducer.framework.exception.KafkaSerializationException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class KafkaEnvelope {
    private String vers = "";
    private String name = "";
    private String payload = "";

    public KafkaEnvelope(TopicItem payload, String version) throws KafkaSerializationException {
        vers = version;
        this.payload = payload.buildPayload();

        String name = payload.getClass().getCanonicalName();
        this.name = name.substring(name.lastIndexOf(".") + 1);
    }


}
