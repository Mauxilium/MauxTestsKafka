package it.mauxilium.MauxKafkaProducer.framework.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SendSampleException extends RuntimeException {
    int failedSample;
    int expectedToSend;
    int failedSession;
    String topic;
    String error;
}



