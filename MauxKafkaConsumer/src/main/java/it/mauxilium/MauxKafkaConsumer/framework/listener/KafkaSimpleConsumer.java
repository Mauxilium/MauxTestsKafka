package it.mauxilium.MauxKafkaConsumer.framework.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@ConditionalOnProperty(value = "CONSUMER_TYPE", havingValue = "simple", matchIfMissing = true)
public class KafkaSimpleConsumer extends BaseConsumer {

    @PostConstruct
    public void setup() {
        log.info("Created KafkaSimpleConsumer");
    }

    @Autowired
    @Qualifier("Topic_Name")
    private String topicName;

    @Autowired
    @Qualifier("Consumer_Group")
    private String consumerGroup;

    @KafkaListener(topics = "${TOPIC_NAME:sample-p1}", groupId = "${CONSUMER_GROUP:simple-group}")
    public void consumeMessage(String messageFromTopic) {
        consume(messageFromTopic, topicName, consumerGroup);
    }

//    @KafkaListener(topics = KafkaDefinitions.TOPIC_ONE_PARTITION, groupId = KafkaDefinitions.SIMPLE_GROUP)
//    public void consumeMessage(String messageFromTopic) {
//        consume(messageFromTopic, KafkaDefinitions.TOPIC_ONE_PARTITION, KafkaDefinitions.SIMPLE_GROUP, "Simple-p1");
//    }
//
//    @KafkaListener(topics = KafkaDefinitions.TOPIC_TWO_PARTITIONS, groupId = KafkaDefinitions.SIMPLE_GROUP)
//    public void consumeMessageTwoPartition(String messageFromTopic) {
//        consume(messageFromTopic, KafkaDefinitions.TOPIC_TWO_PARTITIONS, KafkaDefinitions.SIMPLE_GROUP, "Simple-p2");
//    }
}
