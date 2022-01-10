package business.usecase;

import it.mauxilium.MauxKafkaProducer.business.connector.BrokerConnector;
import it.mauxilium.MauxKafkaProducer.business.model.MessageToSend;
import it.mauxilium.MauxKafkaProducer.business.model.SetupStatusResult;
import it.mauxilium.MauxKafkaProducer.business.model.TestSessionProfile;
import it.mauxilium.MauxKafkaProducer.business.usecase.TestSessionPerformer;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestSessionPerformerTest {

    @Mock
    private BrokerConnector brokerConnector;

    TestSessionPerformer instance; // = new TestSessionPerformer(brokerConnector);

    @Before
    public void setup() {
        instance = new TestSessionPerformer(brokerConnector);
    }

    @Test
    public void setupTest_OK() {
        TestSessionProfile profile = new TestSessionProfile(23, 33, "test-topic");

        SetupStatusResult setupResult = instance.setup(profile);

        Assertions.assertEquals(SetupStatusResult.OK, setupResult);
    }

    @Test
    public void setupTest_InvalidStreamSize_OK() {
        TestSessionProfile profile = new TestSessionProfile(0, 0, "test-topic");

        SetupStatusResult setupResult = instance.setup(profile);

        Assertions.assertEquals(SetupStatusResult.INVALID_STREAM_SIZE, setupResult);
    }

    @Test
    public void setupTest_NoDelay_OK() {
        TestSessionProfile profile = new TestSessionProfile(23, 0, "test-topic");

        SetupStatusResult setupResult = instance.setup(profile);

        Assertions.assertEquals(SetupStatusResult.OK, setupResult);
    }

    @Test
    public void setupTest_InvalidDelay_FAILS() {
        TestSessionProfile profile = new TestSessionProfile(23, -1, "test-topic");

        SetupStatusResult setupResult = instance.setup(profile);

        Assertions.assertEquals(SetupStatusResult.INVALID_DELAY_VALUE, setupResult);
    }

    @Test
    public void setupTest_EmptyTopic_FAILS() {
        TestSessionProfile profile = new TestSessionProfile(23, 33, "");

        SetupStatusResult setupResult = instance.setup(profile);

        Assertions.assertEquals(SetupStatusResult.INVALID_EMPTY_TOPIC_NAME, setupResult);
    }

    @Test
    public void setupTest_InvalidUppercaseTopic_FAILS() {
        TestSessionProfile profile = new TestSessionProfile(23, 33, "TestTopic");

        SetupStatusResult setupResult = instance.setup(profile);

        Assertions.assertEquals(SetupStatusResult.INVALID_TOPIC_NAME, setupResult);
    }

    @Test
    public void setupTest_InvalidSimbolTopic_FAILS() {
        TestSessionProfile profile = new TestSessionProfile(23, 33, "test_#_topic");

        SetupStatusResult setupResult = instance.setup(profile);

        Assertions.assertEquals(SetupStatusResult.INVALID_TOPIC_NAME, setupResult);
    }

    @Test
    public void goodExecution() {
        instance.setup(new TestSessionProfile(18, 77, "test-topic2"));

        instance.execute();

        Mockito.verify(brokerConnector, Mockito.times(18))
                .send(Mockito.anyString(), Mockito.any(MessageToSend.class));
    }

}