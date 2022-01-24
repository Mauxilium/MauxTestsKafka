package it.mauxilium.MauxKafkaConsumer.adapter.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvalidMessageReceivedExceptionTest {

    @Test
    public void instanceTest() {
        InvalidMessageReceivedException instance = new InvalidMessageReceivedException(
                "Fake Body",
                new RuntimeException("Test"));

        Assertions.assertEquals("InvalidMessageReceivedException(invalidBody=Fake Body)", instance.toString());
    }

}