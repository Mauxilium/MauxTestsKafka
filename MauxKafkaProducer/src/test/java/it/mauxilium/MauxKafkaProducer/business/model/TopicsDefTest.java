package it.mauxilium.MauxKafkaProducer.business.model;

import org.junit.Assert;
import org.junit.Test;

public class TopicsDefTest {

    @Test
    public void validateCompleteness() {
        Assert.assertEquals("sample-p1", KafkaDefinitions.TOPIC_ONE_PARTITION);
        Assert.assertEquals("sample-p2", KafkaDefinitions.TOPIC_TWO_PARTITIONS);

        Assert.assertEquals("simple-group", KafkaDefinitions.SIMPLE_GROUP);
        Assert.assertEquals("green-group", KafkaDefinitions.GREEN_GROUP);
        Assert.assertEquals("white-group", KafkaDefinitions.WHITE_GROUP);
        Assert.assertEquals("red-group", KafkaDefinitions.RED_GROUP);
    }

}