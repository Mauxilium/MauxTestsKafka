package business.model;

import it.mauxilium.MauxKafkaProducer.business.model.TestSessionProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestProfileTest {

    @Test
    public void setupTest() {
        TestSessionProfile instance = new TestSessionProfile(111, 1, "TestTopic");

        Assertions.assertEquals(111, instance.getNumSampleToSend());
        Assertions.assertEquals("TestTopic", instance.getTopic());
    }

}