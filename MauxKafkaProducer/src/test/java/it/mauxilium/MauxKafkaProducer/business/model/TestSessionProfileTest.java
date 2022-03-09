package it.mauxilium.MauxKafkaProducer.business.model;

import org.junit.Assert;
import org.junit.Test;

public class TestSessionProfileTest {

    @Test
    public void validateModelCompleteness() {
        TestSessionProfile instance = new TestSessionProfile(KafkaDefinitions.TOPIC_ONE_PARTITION, 1, 2, 3);

        // Assigement check
        Assert.assertEquals(1, instance.getNumSampleToSend());
        Assert.assertEquals(2, instance.getDelayMillisec());
        Assert.assertEquals(3, instance.getReceiverSleepMSec());

        // Completeness check
        Assert.assertEquals(
                "TestSessionProfile(numSampleToSend=1, delayMillisec=2, receiverSleepMSec=3)",
                instance.toString());
    }

}