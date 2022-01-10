package it.mauxilium.MauxKafkaProducer.business.model;

import it.mauxilium.MauxKafkaProducer.framework.model.TopicItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class PayloadToSend extends TopicItem {
    private int itemIndex;
    private int howToSend;
    private String topic;
    private int sessionId;
}
