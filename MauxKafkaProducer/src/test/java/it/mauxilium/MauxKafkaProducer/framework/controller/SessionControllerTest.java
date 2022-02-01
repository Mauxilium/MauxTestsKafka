package it.mauxilium.MauxKafkaProducer.framework.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import it.mauxilium.MauxKafkaProducer.business.model.KafkaDefinitions;
import it.mauxilium.MauxKafkaProducer.framework.model.RequestModel;
import it.mauxilium.MauxKafkaProducer.framework.service.SessionExecutorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SessionExecutorService sessionExecutorService;

    @Test
    public void sessionSetupOk() throws Exception {
        Mockito.when(sessionExecutorService.sessionSetup(Mockito.any(RequestModel.class))).thenReturn("Ok");
        RequestModel externalRequest = new RequestModel(10, 59, KafkaDefinitions.TOPIC_ONE_PARTITION, 123);
        String requestJson = objectMapper.writeValueAsString(externalRequest);
        RequestBuilder postRequest = MockMvcRequestBuilders
                .post("/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        MvcResult mvcResult = mockMvc.perform(postRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Assert.assertEquals("Ok", mvcResult.getResponse().getContentAsString());
        Mockito.verify(sessionExecutorService).sessionSetup(Mockito.any(RequestModel.class));
        Mockito.verifyNoMoreInteractions(sessionExecutorService);
    }

    @Test
    public void sessionRunOk() throws Exception {
        Mockito.when(sessionExecutorService.sessionExecute()).thenReturn("Executed");
        RequestBuilder getRequest = MockMvcRequestBuilders.get("/test");

        MvcResult mvcResult = mockMvc.perform(getRequest).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertEquals("Executed", mvcResult.getResponse().getContentAsString());
        Mockito.verify(sessionExecutorService).sessionExecute();
        Mockito.verifyNoMoreInteractions(sessionExecutorService);
    }

}