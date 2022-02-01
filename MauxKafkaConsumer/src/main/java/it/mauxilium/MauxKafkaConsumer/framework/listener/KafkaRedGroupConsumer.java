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

    @KafkaListener(topics = KafkaDefinitions.TOPIC_ONE_PARTITION, groupId = KafkaDefinitions.RED_GROUP)
    public void consumeMessage(String messageFromTopic) {
        consume(messageFromTopic, KafkaDefinitions.TOPIC_ONE_PARTITION, KafkaDefinitions.RED_GROUP, "Red-p1");
    }

    @KafkaListener(topics = KafkaDefinitions.TOPIC_TWO_PARTITIONS, groupId = KafkaDefinitions.RED_GROUP)
    public void consumeMessageTwoPartition(String messageFromTopic) {
        consume(messageFromTopic, KafkaDefinitions.TOPIC_TWO_PARTITIONS, KafkaDefinitions.RED_GROUP, "Red-p2");
    }
}
