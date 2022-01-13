package it.mauxilium.MauxKafkaProducer.framework.controller;

import io.swagger.v3.oas.annotations.Operation;
import it.mauxilium.MauxKafkaProducer.framework.model.RequestModel;
import it.mauxilium.MauxKafkaProducer.framework.service.SessionExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/test")
public class SessionController {

    @Autowired
    private SessionExecutorService sessionExecutorService;

    @PostMapping
    @Operation(summary = "Define the properties for the next test sessions")
    public ResponseEntity<String> sessionSetup(@RequestBody RequestModel requestModel) {
        log.debug("SESSION SETUP: {}", requestModel);
        String result = sessionExecutorService.sessionSetup(requestModel);
        log.debug("SESSION SETUP RESULT: {}", result);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Execute a new send session using the active session properties")
    public ResponseEntity<String> sessionRun() {
        log.debug("SESSION RUN");
        String result = sessionExecutorService.sessionExecute();
        log.debug("SESSION ENDS");
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
