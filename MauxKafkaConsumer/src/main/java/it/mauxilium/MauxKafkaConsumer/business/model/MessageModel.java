package it.mauxilium.MauxKafkaConsumer.business.model;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@AllArgsConstructor
public class MessageModel {
    int itemIndex;
    int howToSend;
    String topic;
    int sessionId;
}
