package business.usecase;

import it.mauxilium.MauxKafkaProducer.business.connector.BrokerConnector;
import it.mauxilium.MauxKafkaProducer.business.model.MessageToSend;
import it.mauxilium.MauxKafkaProducer.business.model.SetupStatusResult;
import it.mauxilium.MauxKafkaProducer.business.model.TestSessionProfile;
import it.mauxilium.MauxKafkaProducer.business.usecase.TestSessionPerformer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestSessionPerformerTest {

    @Mock
    private BrokerConnector brokerConnector;

    @InjectMocks
    TestSessionPerformer instance = new TestSessionPerformer(brokerConnector);

    @Test
    public void goodSetupTest() {
        TestSessionProfile profile = new TestSessionProfile(23, "TestTopic");

        SetupStatusResult setupResult = instance.setup(profile);

        Assertions.assertEquals(SetupStatusResult.OK, setupResult);
    }

    @Test
    public void goodExecution() {
        instance.setup(new TestSessionProfile(18, "TestTopic2"));

        instance.execute();

        Mockito.verify(brokerConnector, Mockito.times(18))
                .send(Mockito.anyString(), Mockito.any(MessageToSend.class));
    }

}