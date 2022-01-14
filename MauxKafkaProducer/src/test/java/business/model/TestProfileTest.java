package business.model;

import it.mauxilium.MauxKafkaProducer.business.model.TestSessionProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestProfileTest {

    @Test
    public void setupTest() {
        TestSessionProfile instance = new TestSessionProfile(111, 1, "TestTopic", 65);

        Assertions.assertEquals(111, instance.getNumSampleToSend());
        Assertions.assertEquals(1, instance.getDelayMillisec());
        Assertions.assertEquals("TestTopic", instance.getTopic());
        Assertions.assertEquals(65, instance.getReceiverSleepMSec());
    }

}