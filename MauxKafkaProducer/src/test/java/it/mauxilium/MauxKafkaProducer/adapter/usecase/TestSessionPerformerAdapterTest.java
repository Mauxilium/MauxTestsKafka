package it.mauxilium.MauxKafkaProducer.adapter.usecase;

import it.mauxilium.MauxKafkaProducer.adapter.connector.BrokerConnectorAdapter;
import it.mauxilium.MauxKafkaProducer.business.model.KafkaDefinitions;
import it.mauxilium.MauxKafkaProducer.business.model.MessageModel;
import it.mauxilium.MauxKafkaProducer.business.model.SetupStatusResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestSessionPerformerAdapterTest {

    @Mock
    private BrokerConnectorAdapter brokerConnectorAdapter;

    private TestSessionPerformerAdapter instance;

    @Before
    public void setup() {
        instance = new TestSessionPerformerAdapter(brokerConnectorAdapter);
    }

    @Test
    public void sessionSetupOk() {
        SetupStatusResult result = instance.sessionSetup(KafkaDefinitions.TOPIC_ONE_PARTITION,22, 543, 55);
        Assert.assertEquals(SetupStatusResult.OK, result);
    }

    @Test
    public void sessionExecute() {
        SetupStatusResult result = instance.sessionSetup(KafkaDefinitions.TOPIC_ONE_PARTITION,7, 0, 0);
        instance.sessionExecute();

        Mockito.verify(brokerConnectorAdapter, Mockito.times(7)).send(Mockito.anyString(), Mockito.any(MessageModel.class));
    }


}