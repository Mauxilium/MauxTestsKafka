package it.mauxilium.MauxKafkaConsumer.adapter.exception;

import lombok.ToString;

@ToString
public class InvalidMessageReceivedException extends RuntimeException {
    private final String invalidBody;

    public InvalidMessageReceivedException(String invalidBody, Throwable exception) {
        super(exception);
        this.invalidBody = invalidBody;
    }
}
