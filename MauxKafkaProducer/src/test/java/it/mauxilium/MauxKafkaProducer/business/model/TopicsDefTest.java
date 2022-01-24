package it.mauxilium.MauxKafkaProducer.business.model;

import org.junit.Assert;
import org.junit.Test;

public class TopicsDefTest {

    @Test
    public void validateCompleteness() {
        Assert.assertEquals("sample-p1", TopicsDef.TOPIC_ONE_PARTITION);
        Assert.assertEquals("sample-p2", TopicsDef.TOPIC_TWO_PARTITIONS);

        Assert.assertEquals("simple-group", TopicsDef.SIMPLE_GROUP);
        Assert.assertEquals("green-group", TopicsDef.GREEN_GROUP);
        Assert.assertEquals("white-group", TopicsDef.WHITE_GROUP);
        Assert.assertEquals("red-group", TopicsDef.RED_GROUP);
    }

}