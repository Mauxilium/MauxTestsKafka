package it.mauxilium.MauxKafkaConsumer.framework.listener;

import it.mauxilium.MauxKafkaConsumer.adapter.usecase.ReceivedMessageAdapterUC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "SAMPLE_VALUE", groupId = "Processing")
    public void consume(String message) {
        ReceivedMessageAdapterUC.consumeMessage(message);
    }

}
