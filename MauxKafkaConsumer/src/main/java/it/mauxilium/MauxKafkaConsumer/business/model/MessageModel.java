package it.mauxilium.MauxKafkaConsumer.business.model;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

import java.util.Date;

@Value
@ToString
@AllArgsConstructor
public class MessageModel {
    int itemIndex;
    int howToSend;
    String topic;
    int sessionId;
    Date timeStamp;
}
