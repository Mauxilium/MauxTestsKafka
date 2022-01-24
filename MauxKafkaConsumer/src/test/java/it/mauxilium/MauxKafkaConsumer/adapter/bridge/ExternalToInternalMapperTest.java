package it.mauxilium.MauxKafkaConsumer.adapter.bridge;

import it.mauxilium.MauxKafkaConsumer.business.model.MessageModel;
import it.mauxilium.MauxKafkaConsumer.framework.model.MessageOnNetwork;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExternalToInternalMapperTest {

    @Test
    public void remapTest() throws ParseException {
        Date testDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2022-11-18 18:18:18");
        MessageOnNetwork source = Mockito.mock(MessageOnNetwork.class);
        Mockito.when(source.getItemIndex()).thenReturn(3);
        Mockito.when(source.getHowToSend()).thenReturn(12);
        Mockito.when(source.getTopic()).thenReturn("TTT");
        Mockito.when(source.getSessionId()).thenReturn(76);
        Mockito.when(source.getTimeStamp()).thenReturn(testDate);
        Mockito.when(source.getReceiverSleepMSec()).thenReturn(586L);

        MessageModel result = ExternalToInternalMapper.remap.apply(source);

        Assertions.assertEquals(3, result.getItemIndex());
        Assertions.assertEquals(12, result.getHowToSend());
        Assertions.assertEquals("TTT", result.getTopic());
        Assertions.assertEquals(76, result.getSessionId());
        Assertions.assertEquals(testDate, result.getTimeStamp());
        Assertions.assertEquals(586, result.getReceiverSleepMSec());

        Mockito.verify(source).getItemIndex();
        Mockito.verify(source).getHowToSend();
        Mockito.verify(source).getTopic();
        Mockito.verify(source).getSessionId();
        Mockito.verify(source).getTimeStamp();
        Mockito.verify(source).getReceiverSleepMSec();
        Mockito.verifyNoMoreInteractions(source);
    }

}