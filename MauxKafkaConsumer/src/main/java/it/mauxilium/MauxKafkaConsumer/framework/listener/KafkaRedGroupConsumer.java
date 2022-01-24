package it.mauxilium.MauxKafkaConsumer.framework.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@ConditionalOnProperty(value = "CONSUMER_TYPE", havingValue = "red")
public class KafkaRedGroupConsumer extends BaseConsumer {

    @PostConstruct
    public void setup() {
        log.info("Created KafkaRedGroupConsumer");
    }

    @KafkaListener(topics = TopicsDef.TOPIC_ONE_PARTITION, groupId = TopicsDef.RED_GROUP)
    public void consumeMessage(String messageFromTopic) {
        consume(messageFromTopic, TopicsDef.TOPIC_ONE_PARTITION, TopicsDef.RED_GROUP, "Red-p1");
    }

    @KafkaListener(topics = TopicsDef.TOPIC_TWO_PARTITIONS, groupId = TopicsDef.RED_GROUP)
    public void consumeMessageTwoPartition(String messageFromTopic) {
        consume(messageFromTopic, TopicsDef.TOPIC_TWO_PARTITIONS, TopicsDef.RED_GROUP, "Red-p2");
    }
}
