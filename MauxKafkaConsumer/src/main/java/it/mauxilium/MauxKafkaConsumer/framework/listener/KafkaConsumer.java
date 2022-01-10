package it.mauxilium.MauxKafkaConsumer.framework.listener;

import it.mauxilium.MauxKafkaConsumer.adapter.exception.InvalidMessageReceivedException;
import it.mauxilium.MauxKafkaConsumer.adapter.usecase.ReceivedMessageAdapterUC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "SAMPLE_VALUE", groupId = "Processing")
    public void consume(String message) {
        try {
            ReceivedMessageAdapterUC.consumeMessage(message);
        } catch (InvalidMessageReceivedException ex) {
            log.error("FAILED: ", ex);  // Kibana will use this tag to populate ERROR dashbord
        }
    }

}
