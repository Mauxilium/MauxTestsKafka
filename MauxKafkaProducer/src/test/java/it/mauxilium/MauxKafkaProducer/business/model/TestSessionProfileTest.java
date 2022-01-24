package it.mauxilium.MauxKafkaProducer.business.model;

import org.junit.Assert;
import org.junit.Test;

public class TestSessionProfileTest {

    @Test
    public void validateModelCompleteness() {
        TestSessionProfile instance = new TestSessionProfile(1, 2, "T", 3);

        // Assigement check
        Assert.assertEquals(1, instance.getNumSampleToSend());
        Assert.assertEquals(2, instance.getDelayMillisec());
        Assert.assertEquals("T", instance.getTopic());
        Assert.assertEquals(3, instance.getReceiverSleepMSec());

        // Completeness check
        Assert.assertEquals(
                "TestSessionProfile(numSampleToSend=1, delayMillisec=2, topic=T, receiverSleepMSec=3)",
                instance.toString());
    }

}