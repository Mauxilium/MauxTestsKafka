package it.mauxilium.MauxKafkaProducer.framework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Test Session Service", description = "Kafka producer configuration and running")
public class SessionController {

    @Autowired
    private SessionExecutorService sessionExecutorService;

    @PostMapping
    @Operation(summary = "Defines the properties of test sessions")
    public ResponseEntity<String> sessionSetup(@RequestBody RequestModel requestModel) {
        log.debug("SESSION SETUP: {}", requestModel);
        String result = sessionExecutorService.sessionSetup(requestModel);
        log.debug("SESSION SETUP RESULT: {}", result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    @Operation(summary = "Executes a new send session using the 'posted' session properties")
    public ResponseEntity<String> sessionRun() {
        log.debug("SESSION RUN");
        String result = sessionExecutorService.sessionExecute();
        log.debug("SESSION ENDS");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
