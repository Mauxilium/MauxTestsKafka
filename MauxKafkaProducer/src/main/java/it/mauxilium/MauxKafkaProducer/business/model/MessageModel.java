package it.mauxilium.MauxKafkaProducer.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
public class MessageModel {
    private int itemIndex;
    private int howToSend;
    private String topic;
    private int sessionId;
    private Date timestamp;
}
