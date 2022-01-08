package it.mauxilium.MauxKafkaProducer.framework.connector.utils;

import it.mauxilium.MauxKafkaProducer.framework.exception.KafkaSerializationException;
import it.mauxilium.MauxKafkaProducer.framework.model.KafkaEnvelope;
import it.mauxilium.MauxKafkaProducer.framework.model.TopicItem;
import it.mauxilium.MauxKafkaProducer.framework.connector.utils.EnvelopeSerializer;

public class KafkaEnvelopeBuilder {

    public static String serializeEnvelopeOf(TopicItem envelope) throws KafkaSerializationException {
        KafkaEnvelope ke = new KafkaEnvelope(envelope, "00");
        return EnvelopeSerializer.serialize(ke);
    }
}
