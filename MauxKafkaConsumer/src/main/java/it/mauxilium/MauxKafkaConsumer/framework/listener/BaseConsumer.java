package it.mauxilium.MauxKafkaConsumer.framework.listener;

import it.mauxilium.MauxKafkaConsumer.framework.usecase.MessageFromTopicUC;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseConsumer {

    protected void consume(String messageFromTopic, String topic, String group, String type) {
        log.debug("Consumer type={} on topic={} with group={} receive msg: {}", type, topic, group, messageFromTopic);
        if (MessageFromTopicUC.manageMessage.apply(messageFromTopic)) {
            // send ack
        } else {
            // send nack
        }
    }
}
