package it.mauxilium.MauxKafkaConsumer.adapter.usecase;

import it.mauxilium.MauxKafkaConsumer.adapter.bridge.ExternalToInternalMapper;
import it.mauxilium.MauxKafkaConsumer.business.model.MessageModel;
import it.mauxilium.MauxKafkaConsumer.business.usecase.ReceiveMessageUC;
import it.mauxilium.MauxKafkaConsumer.framework.model.MessageOnNetwork;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceivedMessageAdapterUC {

    public static void consumeMessage(MessageOnNetwork deserializedMsg) {

        MessageModel messageModel = ExternalToInternalMapper.remap.apply(deserializedMsg);
        log.debug("Remapped as: {}", messageModel);

        ReceiveMessageUC.useMessage(messageModel);
    }
}
