package it.mauxilium.MauxKafkaProducer.framework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.mauxilium.MauxKafkaProducer.framework.model.RequestModel;
import it.mauxilium.MauxKafkaProducer.framework.service.SessionExecutorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9093", "port=9093"})
public class SessionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SessionExecutorService sessionExecutorService;

    @Test
    public void sessionSetupTest() throws Exception {
        RequestModel externalRequest = new RequestModel(10, 59, 123);
        String requestJson = objectMapper.writeValueAsString(externalRequest);
        RequestBuilder postRequest = MockMvcRequestBuilders
                .post("/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        MvcResult mvcResult = mockMvc.perform(postRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Assertions.assertEquals(201, mvcResult.getResponse().getStatus());
        Assertions.assertEquals("Done", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testSessionRun() throws Exception {
        RequestBuilder getRequest = MockMvcRequestBuilders.get("/test");

        MvcResult mvcResult = mockMvc.perform(getRequest).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertEquals("Done", mvcResult.getResponse().getContentAsString());
    }
}
