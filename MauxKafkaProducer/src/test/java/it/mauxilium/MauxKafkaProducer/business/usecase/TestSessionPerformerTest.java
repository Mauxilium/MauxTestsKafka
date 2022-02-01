package it.mauxilium.MauxKafkaProducer.business.usecase;

import it.mauxilium.MauxKafkaProducer.business.connector.BrokerConnector;
import it.mauxilium.MauxKafkaProducer.business.model.MessageModel;
import it.mauxilium.MauxKafkaProducer.business.model.SetupStatusResult;
import it.mauxilium.MauxKafkaProducer.business.model.TestSessionProfile;
import it.mauxilium.MauxKafkaProducer.business.model.KafkaDefinitions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestSessionPerformerTest {

    @Mock
    private BrokerConnector brokerConnector;

    @Mock
    private TestSessionProfile testProfile;

    private TestSessionPerformer instance;

    @Before
    public void testSetup() {
        instance = new TestSessionPerformer(brokerConnector);
    }

    private void mockSetup(int numToSend, long sendDelay, String topic, long receiveDelay) {
        Mockito.when(testProfile.getNumSampleToSend()).thenReturn(numToSend);
        Mockito.when(testProfile.getDelayMillisec()).thenReturn(sendDelay);
        Mockito.when(testProfile.getTopic()).thenReturn(topic);
        Mockito.when(testProfile.getReceiverSleepMSec()).thenReturn(receiveDelay);
    }

    private void mockVerification() {
        Mockito.verify(testProfile).getNumSampleToSend();
        Mockito.verify(testProfile).getDelayMillisec();
        Mockito.verify(testProfile).getTopic();
        Mockito.verify(testProfile).getReceiverSleepMSec();
        Mockito.verifyNoMoreInteractions(testProfile);
    }

    @Test
    public void setupOk() {
        mockSetup(1, 2, KafkaDefinitions.TOPIC_ONE_PARTITION, 3);

        SetupStatusResult result = instance.setup(testProfile);

        Assert.assertEquals(SetupStatusResult.OK, result);
        mockVerification();
        // Setup do not involve broker connector
        Mockito.verifyNoMoreInteractions(brokerConnector);
    }

    @Test
    public void setup_wrongNumToSend() {
        mockSetup(-22, 0, KafkaDefinitions.TOPIC_ONE_PARTITION, 3);

        SetupStatusResult result = instance.setup(testProfile);

        Assert.assertEquals(SetupStatusResult.INVALID_STREAM_SIZE, result);
        mockVerification();
        // Setup do not involve broker connector
        Mockito.verifyNoMoreInteractions(brokerConnector);
    }

    @Test
    public void setup_wrongSendDelay() {
        mockSetup(6, -1, KafkaDefinitions.TOPIC_TWO_PARTITIONS, 3);

        SetupStatusResult result = instance.setup(testProfile);

        Assert.assertEquals(SetupStatusResult.INVALID_DELAY_VALUE, result);
        mockVerification();
        // Setup do not involve broker connector
        Mockito.verifyNoMoreInteractions(brokerConnector);
    }

    @Test
    public void setup_wrongEmptyDestinationTopic() {
        mockSetup(6, 99, "", 3);

        SetupStatusResult result = instance.setup(testProfile);

        Assert.assertEquals(SetupStatusResult.INVALID_EMPTY_TOPIC_NAME, result);
        mockVerification();
        // Setup do not involve broker connector
        Mockito.verifyNoMoreInteractions(brokerConnector);
    }

    @Test
    public void setup_invalidDestinationTopic() {
        mockSetup(6, 32, "topic_no_#", 3);

        SetupStatusResult result = instance.setup(testProfile);

        Assert.assertEquals(SetupStatusResult.INVALID_TOPIC_NAME, result);
        mockVerification();
        // Setup do not involve broker connector
        Mockito.verifyNoMoreInteractions(brokerConnector);
    }

    @Test
    public void setup_invalidDestinationTopicName() {
        mockSetup(6, 623, "topic-11", 0);

        SetupStatusResult result = instance.setup(testProfile);

        Assert.assertEquals(SetupStatusResult.UNEXPECTED_TOPIC_NAME, result);
        mockVerification();
        // Setup do not involve broker connector
        Mockito.verifyNoMoreInteractions(brokerConnector);
    }

    @Test
    public void setup_wrongReceiverDelay() {
        mockSetup(6, 55, KafkaDefinitions.TOPIC_TWO_PARTITIONS, -76);

        SetupStatusResult result = instance.setup(testProfile);

        Assert.assertEquals(SetupStatusResult.INVALID_RECEIVER_SLEEP_VALUE, result);
        mockVerification();
        // Setup do not involve broker connector
        Mockito.verifyNoMoreInteractions(brokerConnector);
    }

    @Test
    public void sendMessagesOk() {
        mockSetup(123, 55, KafkaDefinitions.TOPIC_TWO_PARTITIONS, 7);

        SetupStatusResult result = instance.setup(testProfile);
        instance.execute();

        Mockito.verify(brokerConnector, Mockito.times(123))
                .send(Mockito.anyString(), Mockito.any(MessageModel.class));
    }

}