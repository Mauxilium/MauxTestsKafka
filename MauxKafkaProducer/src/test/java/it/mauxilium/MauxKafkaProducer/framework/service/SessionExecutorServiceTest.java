package it.mauxilium.MauxKafkaProducer.framework.service;

import it.mauxilium.MauxKafkaProducer.business.model.KafkaDefinitions;
import it.mauxilium.MauxKafkaProducer.framework.exception.SessionSetupException;
import it.mauxilium.MauxKafkaProducer.framework.model.RequestModel;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

@RunWith(MockitoJUnitRunner.class)
public class SessionExecutorServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    private RequestModel requestModel;

    private SessionExecutorService instance;

    @Before
    public void setUp() {
        instance = new SessionExecutorService();
        instance.setup();
    }

    private void mockSetup(int streamSize, long delayMillisec, String topic, long receiverSleep) {
        requestModel = new RequestModel(streamSize, delayMillisec, topic, receiverSleep);
    }

    @Test
    public void setupOk() {
        mockSetup(3, 4, KafkaDefinitions.TOPIC_ONE_PARTITION, 55);

        String result = instance.sessionSetup(requestModel);

        Assert.assertEquals("Done", result);
    }

    @Test
    public void setupFails_StreamSize() {
        mockSetup(0, 4, KafkaDefinitions.TOPIC_ONE_PARTITION, 55);

        Assertions.assertThatThrownBy(() -> instance.sessionSetup(requestModel))
                .isInstanceOf(SessionSetupException.class)
                .hasMessage("Invalid number of messages to send, must be > 0, found: 0");
    }

    @Test
    public void setupFails_DelayValue() {
        mockSetup(11, -3, KafkaDefinitions.TOPIC_ONE_PARTITION, 55);

        Assertions.assertThatThrownBy(() -> instance.sessionSetup(requestModel))
                .isInstanceOf(SessionSetupException.class)
                .hasMessage("Invalid delay between messages, must be > 0, found: -3");
    }

    @Test
    public void setupFails_EmptyTopic() {
        mockSetup(11, 87, "", 55);

        Assertions.assertThatThrownBy(() -> instance.sessionSetup(requestModel))
                .isInstanceOf(SessionSetupException.class)
                .hasMessage("Invalid empty destination topic");
    }

    @Test
    public void setupFails_InvalidTopic() {
        mockSetup(11, 87, "Not_Valid_Topic_#", 55);

        Assertions.assertThatThrownBy(() -> instance.sessionSetup(requestModel))
                .isInstanceOf(SessionSetupException.class)
                .hasMessage("Invalid destination topic name: Not_Valid_Topic_#");
    }

    @Test
    public void setupFails_UnexpectedTopic() {
        mockSetup(11, 87, "unknown-topic", 55);

        Assertions.assertThatThrownBy(() -> instance.sessionSetup(requestModel))
                .isInstanceOf(SessionSetupException.class)
                .hasMessage("Invalid destination topic name: unknown-topic; is not one of the expected [sample-p1, sample-p2]");
    }

    @Test
    public void sessionExecTest() {
        String response = instance.sessionExecute();

        Assert.assertEquals("Done", response);
    }

    @Test
    public void sessionExec_FailureTest() {
        mockSetup(11, 87, KafkaDefinitions.TOPIC_ONE_PARTITION, 55);

        instance.sessionSetup(requestModel);
        String response = instance.sessionExecute();

        Assert.assertEquals("SendSampleException(failedSample=1, expectedToSend=11, failedSession=1, topic=sample-p1, error=null; null)", response);
    }
}