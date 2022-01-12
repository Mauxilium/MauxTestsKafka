package it.mauxilium.MauxKafkaConsumer.framework.usecase;

import it.mauxilium.MauxKafkaConsumer.adapter.exception.InvalidMessageReceivedException;
import it.mauxilium.MauxKafkaConsumer.adapter.usecase.ReceivedMessageAdapterUC;
import it.mauxilium.MauxKafkaConsumer.framework.jackson.NetworkDeserializer;
import it.mauxilium.MauxKafkaConsumer.framework.model.MessageOnNetwork;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j
public class MessageFromTopicUC {

    public static Function<String, Boolean> manageMessage = MessageFromTopicUC::consumeMsg;

    private static boolean consumeMsg(String msgFromTopic) {
        log.debug("Payload received: {}", msgFromTopic);

        try {
            MessageOnNetwork deserializedMsg = NetworkDeserializer.deserialize.apply(msgFromTopic);
            log.debug("Deserialized as: {}", deserializedMsg);

            ReceivedMessageAdapterUC.consumeMessage(deserializedMsg);
        } catch (InvalidMessageReceivedException ex) {
            log.error("FAILED: " + ex, ex);  // Kibana will use this tag to populate ERROR dashbord
            return false;
        }

        return true;
    }
}
