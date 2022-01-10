package it.mauxilium.MauxKafkaConsumer.adapter.exception;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class InvalidMessageReceivedException extends RuntimeException {
    private String invalidBody;
}
