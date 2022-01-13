package it.mauxilium.MauxKafkaConsumer.framework.listener;

import it.mauxilium.MauxKafkaConsumer.framework.usecase.MessageFromTopicUC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@ConditionalOnProperty(value = "WHITE_GROUP")
public class KafkaWhiteGroupConsumer {

    @PostConstruct
    public void setup() {
        log.info("Created service: KafkaWhiteGroupConsumer");
    }

    @KafkaListener(topics = "sample-topic", groupId = "white-group")
    public void sharedConsume(String messageFromTopic) {
        log.debug("WHITE Group consumer receive: {}", messageFromTopic);
        if (MessageFromTopicUC.manageMessage.apply(messageFromTopic)) {
            // send ack
        } else {
            // send nack
        }
    }
}
