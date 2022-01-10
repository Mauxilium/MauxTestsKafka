package it.mauxilium.MauxKafkaConsumer.business.usecase;

import it.mauxilium.MauxKafkaConsumer.business.model.MessageModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceiveMessageUC {

    public static void useMessage(MessageModel messageModel) {
        log.info("RECEIVED: {}", messageModel);  // TODO next enhancement could be Ack/Nack management
    }
}
