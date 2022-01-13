package it.mauxilium.MauxKafkaConsumer.framework.listener;

import it.mauxilium.MauxKafkaConsumer.framework.usecase.MessageFromTopicUC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@ConditionalOnProperty(value = "DEFAULT_GROUP", matchIfMissing = true)
public class KafkaDefaultConsumer {

    @PostConstruct
    public void setup() {
        log.info("Created service: KafkaDefaultConsumer");
    }

    @KafkaListener(topics = "sample-topic", groupId = "default-group")
    public void consumeMessage(String messageFromTopic) {
        log.debug("DEFAULT Group consumer receive: {}", messageFromTopic);
        if (MessageFromTopicUC.manageMessage.apply(messageFromTopic)) {
            // send ack
        } else {
            // send nack
        }
    }
}
