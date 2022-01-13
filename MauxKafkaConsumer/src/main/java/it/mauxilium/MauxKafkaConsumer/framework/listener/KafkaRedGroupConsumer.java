package it.mauxilium.MauxKafkaConsumer.framework.listener;

import it.mauxilium.MauxKafkaConsumer.framework.usecase.MessageFromTopicUC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@ConditionalOnProperty(value = "RED_GROUP")
public class KafkaRedGroupConsumer {

    @PostConstruct
    public void setup() {
        log.info("Created service: KafkaRedGroupConsumer");
    }

    @KafkaListener(topics = "sample-topic", groupId = "red-group")
    public void sharedConsume(String messageFromTopic) {
        log.debug("RED Group consumer receive: {}", messageFromTopic);
        if (MessageFromTopicUC.manageMessage.apply(messageFromTopic)) {
            // send ack
        } else {
            // send nack
        }
    }

    @KafkaListener(topics = "sample-topic2", groupId = "second-group")
    public void consumeMultiple(String messageFromTopic) {
        log.debug("FIRST GROUP CONSUMING: {}", messageFromTopic);
        if (MessageFromTopicUC.manageMessage.apply(messageFromTopic)) {
            // send ack
        } else {
            // send nack
        }
    }

}
