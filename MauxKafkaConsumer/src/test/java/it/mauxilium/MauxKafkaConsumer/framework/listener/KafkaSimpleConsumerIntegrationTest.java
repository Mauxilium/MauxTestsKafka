package it.mauxilium.MauxKafkaConsumer.framework.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.mauxilium.MauxKafkaConsumer.business.model.MessageStorage;
import it.mauxilium.MauxKafkaConsumer.framework.model.MessageOnNetwork;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class KafkaSimpleConsumerIntegrationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaSimpleConsumer kafkaSimpleConsumer;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void receiveOnTopicOne_Test() throws Exception {
        Date testDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2021-02-02 10:23:45.123");
        MessageOnNetwork testMsg = new MessageOnNetwork();
        testMsg.setItemIndex(1);
        testMsg.setHowToSend(22);
        testMsg.setTimeStamp(testDate);
        testMsg.setReceiverSleepMSec(54);
        testMsg.setTopic(KafkaDefinitions.TOPIC_ONE_PARTITION);
        String msgPayload = objectMapper.writeValueAsString(testMsg);
        kafkaTemplate.send(KafkaDefinitions.TOPIC_ONE_PARTITION, msgPayload);

        testMsg.setItemIndex(2);
        kafkaTemplate.send(KafkaDefinitions.TOPIC_ONE_PARTITION, msgPayload);

        do {
            TimeUnit.SECONDS.sleep(1);
        } while (MessageStorage.getInstance().getNumOfMessages() < 2);

        Assert.assertEquals(2, MessageStorage.getInstance().getNumOfMessages());
    }

}