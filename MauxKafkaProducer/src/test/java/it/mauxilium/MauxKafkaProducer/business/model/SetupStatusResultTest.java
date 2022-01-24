package it.mauxilium.MauxKafkaProducer.business.model;

import org.junit.Assert;
import org.junit.Test;

public class SetupStatusResultTest {

    @Test
    public void validateModelCompleteness() {
        Assert.assertEquals("OK", SetupStatusResult.OK.name());
        Assert.assertEquals("INVALID_STREAM_SIZE", SetupStatusResult.INVALID_STREAM_SIZE.name());
        Assert.assertEquals("INVALID_DELAY_VALUE", SetupStatusResult.INVALID_DELAY_VALUE.name());
        Assert.assertEquals("INVALID_EMPTY_TOPIC_NAME", SetupStatusResult.INVALID_EMPTY_TOPIC_NAME.name());
        Assert.assertEquals("INVALID_TOPIC_NAME", SetupStatusResult.INVALID_TOPIC_NAME.name());
        Assert.assertEquals("UNEXPECTED_TOPIC_NAME", SetupStatusResult.UNEXPECTED_TOPIC_NAME.name());
        Assert.assertEquals("INVALID_RECEIVER_SLEEP_VALUE", SetupStatusResult.INVALID_RECEIVER_SLEEP_VALUE.name());

        // Completeness validation
        Assert.assertEquals(SetupStatusResult.values().length, 7);
    }


}