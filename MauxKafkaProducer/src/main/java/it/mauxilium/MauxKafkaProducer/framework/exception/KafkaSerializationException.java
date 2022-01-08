package it.mauxilium.MauxKafkaProducer.framework.exception;

public class KafkaSerializationException extends Exception {

    private final String error;
    private final String msg;

    public KafkaSerializationException(String error, String msg) {
        this.error = error;
        this.msg = msg;
    }
}
