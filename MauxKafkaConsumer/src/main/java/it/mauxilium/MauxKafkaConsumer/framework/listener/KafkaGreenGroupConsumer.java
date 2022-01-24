package it.mauxilium.MauxKafkaConsumer.framework.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@ConditionalOnProperty(value = "CONSUMER_TYPE", havingValue = "green")
public class KafkaGreenGroupConsumer extends BaseConsumer {

    @PostConstruct
    public void setup() {
        log.info("Created KafkaGreenGroupConsumer");
    }

    @KafkaListener(topics = TopicsDef.TOPIC_ONE_PARTITION, groupId = TopicsDef.GREEN_GROUP)
    public void consumeMessage(String messageFromTopic) {
        consume(messageFromTopic, TopicsDef.TOPIC_ONE_PARTITION, TopicsDef.GREEN_GROUP, "Green-p1");
    }

    @KafkaListener(topics = TopicsDef.TOPIC_TWO_PARTITIONS, groupId = TopicsDef.GREEN_GROUP)
    public void consumeMessageTwopartition(String messageFromTopic) {
        consume(messageFromTopic, TopicsDef.TOPIC_TWO_PARTITIONS, TopicsDef.GREEN_GROUP, "Green-p2");
    }
}
