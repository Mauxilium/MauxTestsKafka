package it.mauxilium.MauxKafkaConsumer.framework.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@ConditionalOnProperty(value = "CONSUMER_TYPE", havingValue = "white")
public class KafkaWhiteGroupConsumer extends BaseConsumer {

    @PostConstruct
    public void setup() {
        log.info("Created KafkaWhiteGroupConsumer");
    }

    @KafkaListener(topics = KafkaDefinitions.TOPIC_ONE_PARTITION, groupId = KafkaDefinitions.WHITE_GROUP)
    public void consumeMessage(String messageFromTopic) {
        consume(messageFromTopic, KafkaDefinitions.TOPIC_ONE_PARTITION, KafkaDefinitions.WHITE_GROUP, "White-p1");
    }

    @KafkaListener(topics = KafkaDefinitions.TOPIC_TWO_PARTITIONS, groupId = KafkaDefinitions.WHITE_GROUP)
    public void consumeMessageTwoPartition(String messageFromTopic) {
        consume(messageFromTopic, KafkaDefinitions.TOPIC_TWO_PARTITIONS, KafkaDefinitions.WHITE_GROUP, "White-p2");
    }
}
