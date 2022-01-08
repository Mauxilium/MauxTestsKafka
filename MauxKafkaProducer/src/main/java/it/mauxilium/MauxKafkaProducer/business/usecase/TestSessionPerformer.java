package it.mauxilium.MauxKafkaProducer.business.usecase;

import it.mauxilium.MauxKafkaProducer.business.connector.BrokerConnector;
import it.mauxilium.MauxKafkaProducer.business.model.PayloadToSend;
import it.mauxilium.MauxKafkaProducer.business.model.TestSessionProfile;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class TestSessionPerformer {

    private int sessionId = 0;
    private int howToSend = 0;
    private String destinationTopic;
    private final BrokerConnector brokerConnector;

    public TestSessionPerformer(BrokerConnector brokerConnector) {
        this.brokerConnector = brokerConnector;
    }

    public String setup(TestSessionProfile testProfile) {
        howToSend = testProfile.getNumSampleToSend();
        destinationTopic = testProfile.getTopic();

        if (howToSend <= 0) {
            String response = String.format("Invalid number of messages to send, must be > 0, found: {}", howToSend);
            log.error(response);
            return response;
        }

        if (destinationTopic.isEmpty()) {
            log.error("Invalid empty destination topic");
            String response = "Invalid empty destination topic";
            log.error(response);
            return response;
        }

        return "Test Setup successfully done";
    }

    public void execute() {
        sessionId++;
        IntStream.range(0, howToSend).forEach(this::sendIndex);
    }

    private void sendIndex(int indx) {
        brokerConnector.send(destinationTopic, new PayloadToSend(indx, howToSend, destinationTopic, sessionId));
    }
}
