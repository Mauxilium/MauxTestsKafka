package it.mauxilium.MauxKafkaProducer.business.usecase;

import it.mauxilium.MauxKafkaProducer.business.connector.BrokerConnector;
import it.mauxilium.MauxKafkaProducer.business.model.MessageModel;
import it.mauxilium.MauxKafkaProducer.business.model.SetupStatusResult;
import it.mauxilium.MauxKafkaProducer.business.model.TestSessionProfile;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Slf4j
public class TestSessionPerformer {

    private static final Pattern topicNameLegalChars = Pattern.compile("[a-z0-9.-]*");

    private int sessionId = 0;
    private int howToSend = 0;
    private long delayMillisec;
    private String destinationTopic;
    private final BrokerConnector brokerConnector;

    public TestSessionPerformer(BrokerConnector brokerConnector) {
        this.brokerConnector = brokerConnector;
    }

    public SetupStatusResult setup(TestSessionProfile testProfile) {
        howToSend = testProfile.getNumSampleToSend();
        delayMillisec = testProfile.getDelayMillisec();
        destinationTopic = testProfile.getTopic();

        if (howToSend <= 0) {
            String response = String.format("Invalid number of messages to send, must be > 0, found: {}", howToSend);
            log.error(response);
            return SetupStatusResult.INVALID_STREAM_SIZE;
        }

        if (delayMillisec < 0) {
            String response = String.format("Invalid number of delay between messages send, must be >= 0, found: {}", delayMillisec);
            log.error(response);
            return SetupStatusResult.INVALID_DELAY_VALUE;
        }

        if (destinationTopic.isEmpty()) {
            log.error("Invalid empty destination topic");
            return SetupStatusResult.INVALID_EMPTY_TOPIC_NAME;
        }

        if (!topicNameLegalChars.matcher(destinationTopic).matches()) {
            String response = "Invalid empty destination topic";
            log.error("Invalid topic name (do not matches: [a-z0-9.-]");
            return SetupStatusResult.INVALID_TOPIC_NAME;
        }

        return SetupStatusResult.OK;
    }

    public void execute() {
        sessionId++;
        IntStream.rangeClosed(1, howToSend).forEach(this::sendIndex);
    }

    private void sendIndex(int indx) {
        MessageModel payload = new MessageModel(indx, howToSend, destinationTopic, sessionId, new Date());
        brokerConnector.send(destinationTopic, payload);

        try {
            TimeUnit.MILLISECONDS.sleep(delayMillisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.warn("Sleep between messages generates exception (ignored)", e);
        }
    }
}
