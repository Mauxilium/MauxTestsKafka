package it.mauxilium.MauxKafkaConsumer.framework.usecase;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessageFromTopicUCTest {

    @Test
    public void consumeOk() {
        String jsonMsg = "{\"itemIndex\":1, " +
                "\"howToSend\":65, " +
                "\"topic\":\"Test\", " +
                "\"sessionId\":2, " +
                "\"timeStamp\":\"2022-11-18 12:11:10.678\", " +
                "\"receiverSleepMSec\":98}";

        Boolean result = MessageFromTopicUC.manageMessage.apply(jsonMsg);

        Assertions.assertTrue(result);
    }

    @Test
    public void consumeFails() {
        String jsonMsg = "{\"itemIndex\":1, " +
                "\"howToSend\":65, " +
                "\"topic\":\"Test\", " +
                "\"sessionId\":2, " +
                "\"timeStamp\":\"Invalid  Date\", " +
                "\"receiverSleepMSec\":98}";

        Boolean result = MessageFromTopicUC.manageMessage.apply(jsonMsg);

        Assertions.assertFalse(result);
    }

}