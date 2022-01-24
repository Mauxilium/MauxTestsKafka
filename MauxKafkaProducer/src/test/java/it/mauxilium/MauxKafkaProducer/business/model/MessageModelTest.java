package it.mauxilium.MauxKafkaProducer.business.model;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageModelTest {

    @Test
    public void validateModelCompleteness() throws ParseException {
        Date testDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-11-18 06:50:00");

        MessageModel instance = new MessageModel(1, 2, "T", 3, testDate, 4);

        // Assignement validation
        Assert.assertEquals(1, instance.getItemIndex());
        Assert.assertEquals(2, instance.getHowToSend());
        Assert.assertEquals("T", instance.getTopic());
        Assert.assertEquals(testDate, instance.getTimestamp());
        Assert.assertEquals(4, instance.getReceiverSleepMSec());

        // Completeness validation
        Assert.assertEquals(
                "MessageModel(itemIndex=1, howToSend=2, topic=T, sessionId=3, timestamp=Fri Nov 18 06:50:00 CET 2022, receiverSleepMSec=4)",
                instance.toString());

    }

}