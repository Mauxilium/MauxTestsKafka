package it.mauxilium.MauxKafkaProducer.framework.controller;


import it.mauxilium.MauxKafkaProducer.framework.model.RequestModel;
import it.mauxilium.MauxKafkaProducer.framework.service.SessionExecutorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class SessionControllerTest {

    @Mock
    private SessionExecutorService sessionExecutorService;

    @Mock
    private RequestModel requestModel;

    @InjectMocks
    private SessionController instance = new SessionController();

    @Test
    public void sessionSetupOk() {
        Mockito.when(sessionExecutorService.sessionSetup(requestModel)).thenReturn("Ok");

        ResponseEntity<String> result = instance.sessionSetup(requestModel);

        Assert.assertEquals("Ok", result.getBody());
        Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());

        Mockito.verify(sessionExecutorService).sessionSetup(requestModel);
        Mockito.verifyNoMoreInteractions(sessionExecutorService);
    }

    @Test
    public void sessionRunOk() {
        Mockito.when(sessionExecutorService.sessionExecute()).thenReturn("Executed");

        ResponseEntity<String> result = instance.sessionRun();

        Assert.assertEquals("Executed", result.getBody());
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());

        Mockito.verify(sessionExecutorService).sessionExecute();
        Mockito.verifyNoMoreInteractions(sessionExecutorService);
    }

}