package it.mauxilium.MauxKafkaConsumer.framework.listener;

import it.mauxilium.MauxKafkaConsumer.framework.usecase.MessageFromTopicUC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "sample-topic", groupId = "processing")
    public void consume(String messageFromTopic) {
        if (MessageFromTopicUC.manageMessage.apply(messageFromTopic)) {
            // send ack
        } else {
            // send nack
        }
    }

}
