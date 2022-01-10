package it.mauxilium.MauxKafkaProducer.adapter.usecase;

import it.mauxilium.MauxKafkaProducer.adapter.connector.BrokerConnectorAdapter;
import it.mauxilium.MauxKafkaProducer.business.model.SetupStatusResult;
import it.mauxilium.MauxKafkaProducer.business.model.TestSessionProfile;
import it.mauxilium.MauxKafkaProducer.business.usecase.TestSessionPerformer;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class TestSessionPerformerAdapter {

    TestSessionPerformer testSessionPerformer;

    public TestSessionPerformerAdapter(BrokerConnectorAdapter brokerConnectorAdapter) {
        testSessionPerformer = new TestSessionPerformer(brokerConnectorAdapter);
    }

    public SetupStatusResult sessionSetup(int num, long delayMillisec, String topic) {
        log.debug("Setup with: HowToSend={}, Delay(milliSec)={}, Topic={}", num, delayMillisec, topic);
        TestSessionProfile profile = new TestSessionProfile(num, delayMillisec, topic);
        SetupStatusResult result = testSessionPerformer.setup(profile);
        log.debug("Setup response: {}", result);
        return result;
    }

    public void sessionExecute() {
        log.debug("Session execute begin...");
        testSessionPerformer.execute();
        log.debug("Session execute ends.");
    }
}
