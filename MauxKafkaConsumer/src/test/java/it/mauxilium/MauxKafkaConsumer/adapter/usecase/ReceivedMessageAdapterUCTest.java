package it.mauxilium.MauxKafkaConsumer.adapter.usecase;

import it.mauxilium.MauxKafkaConsumer.framework.model.MessageOnNetwork;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ReceivedMessageAdapterUCTest {

    @Test
    public void consumerOk() {
        MessageOnNetwork deserializedMsg = Mockito.mock(MessageOnNetwork.class);

        ReceivedMessageAdapterUC.consumeMessage(deserializedMsg);

        Mockito.verify(deserializedMsg).getItemIndex();
        Mockito.verify(deserializedMsg).getHowToSend();
        Mockito.verify(deserializedMsg).getTopic();
        Mockito.verify(deserializedMsg).getSessionId();
        Mockito.verify(deserializedMsg).getTimeStamp();
        Mockito.verify(deserializedMsg).getReceiverSleepMSec();
        Mockito.verifyNoMoreInteractions(deserializedMsg);
    }

}