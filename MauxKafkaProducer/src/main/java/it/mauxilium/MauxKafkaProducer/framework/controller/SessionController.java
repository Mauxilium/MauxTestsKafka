package it.mauxilium.MauxKafkaProducer.framework.controller;

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
    public String sessionSetup(@RequestBody RequestModel requestModel) {
        log.debug("SESSION SETUP: {}", requestModel);
        String result = sessionExecutorService.sessionSetup(requestModel);
        log.debug("SESSION SETUP RESULT: {}", result);
        return result;
    }

    @GetMapping
    public boolean sessionRun() {
        log.debug("SESSION RUN");
        boolean result = sessionExecutorService.sessionExecute();
        log.debug("SESSION ENDS");
        return result;
    }
}
