package it.mauxilium.MauxKafkaConsumer.adapter.usecase;

import it.mauxilium.MauxKafkaConsumer.adapter.bridge.ExternalToInternalMapper;
import it.mauxilium.MauxKafkaConsumer.adapter.bridge.NetworkDeserializer;
import it.mauxilium.MauxKafkaConsumer.adapter.model.MessageOnNetworkModel;
import it.mauxilium.MauxKafkaConsumer.business.model.MessageModel;
import it.mauxilium.MauxKafkaConsumer.business.usecase.ReceiveMessageUC;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceivedMessageAdapterUC {

    public static void consumeMessage(String message) {
        log.debug("Payload received: {}", message);

        MessageOnNetworkModel deserializedMsg = NetworkDeserializer.deserialize.apply(message);
        log.debug("Deserialized as: {}", deserializedMsg);

        MessageModel messageModel = ExternalToInternalMapper.remap.apply(deserializedMsg);
        log.debug("Remapped as: {}", messageModel);

        ReceiveMessageUC.useMessage(messageModel);
    }
}
