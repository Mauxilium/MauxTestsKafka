package it.mauxilium.MauxKafkaProducer.framework.controller;

import io.swagger.v3.oas.annotations.Operation;
import it.mauxilium.MauxKafkaProducer.framework.model.RequestModel;
import it.mauxilium.MauxKafkaProducer.framework.service.SessionExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/test")
public class SessionController {

    @Autowired
    private SessionExecutorService sessionExecutorService;

    @PostMapping
    @Operation(summary = "Define the properties for the next test sessions")
    public String sessionSetup(@RequestBody RequestModel requestModel) {
        log.debug("SESSION SETUP: {}", requestModel);
        String result = sessionExecutorService.sessionSetup(requestModel);
        log.debug("SESSION SETUP RESULT: {}", result);
        return result;
    }

    @GetMapping
    @Operation(summary = "Execute a new send session using the active session properties")
    public String sessionRun() {
        log.debug("SESSION RUN");
        String result = sessionExecutorService.sessionExecute();
        log.debug("SESSION ENDS");
        return result;
    }
}
