package it.mauxilium.MauxKafkaConsumer.business.usecase;

import it.mauxilium.MauxKafkaConsumer.business.model.MessageModel;
import org.junit.jupiter.api.Test;

public class ReceiveMessageUCTest {

    @Test
    public void useMsgTest() {
        MessageModel messageModel = new MessageModel(2, 3, "TT", 6, null, 44);

        ReceiveMessageUC.useMessage(messageModel);
    }

}