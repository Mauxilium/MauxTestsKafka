package it.mauxilium.MauxKafkaConsumer.framework.listener;

import it.mauxilium.MauxKafkaConsumer.framework.usecase.MessageFromTopicUC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@ConditionalOnProperty(value = "GREEN_GROUP")
public class KafkaGreenGroupConsumer {

    @PostConstruct
    public void setup() {
        log.info("Created service: KafkaGreenGroupConsumer");
    }

    @KafkaListener(topics = "sample-topic", groupId = "green-group")
    public void consumeMessage(String messageFromTopic) {
        log.debug("GREEN Group consumer receive: {}", messageFromTopic);
        if (MessageFromTopicUC.manageMessage.apply(messageFromTopic)) {
            // send ack
        } else {
            // send nack
        }
    }
}
