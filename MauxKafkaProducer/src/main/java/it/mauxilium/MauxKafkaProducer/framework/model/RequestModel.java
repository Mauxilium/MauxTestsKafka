package it.mauxilium.MauxKafkaProducer.framework.model;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@AllArgsConstructor
public class RequestModel {
    int streamSize;
    String topic;
}
