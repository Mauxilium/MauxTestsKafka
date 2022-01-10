package it.mauxilium.MauxKafkaProducer.business.model;

public enum SetupStatusResult {
    OK,
    INVALID_STREAM_SIZE,
    INVALID_DELAY_VALUE,
    INVALID_EMPTY_TOPIC_NAME,
    INVALID_TOPIC_NAME
}
