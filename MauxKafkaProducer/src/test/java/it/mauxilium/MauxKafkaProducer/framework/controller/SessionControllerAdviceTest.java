package it.mauxilium.MauxKafkaProducer.framework.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SessionControllerAdviceTest {

    private SessionControllerAdvice instance = new SessionControllerAdvice();

    @Test
    public void badRequest() {
        ResponseEntity<String> result = instance.handleSessionSetupError(new RuntimeException("Hello"));

        Assert.assertEquals("Hello", result.getBody());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void internalServerError() {
        ResponseEntity<String> result = instance.handleKafkaError(new RuntimeException("Int error"));

        Assert.assertEquals("Int error", result.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    @Test
    public void sendError() {
        ResponseEntity<String> result = instance.handleSendError(new RuntimeException("Server Error"));

        Assert.assertEquals("Server Error", result.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

}