package it.mauxilium.MauxKafkaConsumer.adapter.model;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@AllArgsConstructor
public class MessageOnNetworkModel {
    int itemIndex;
    int howToSend;
    String topic;
    int sessionId;
}
