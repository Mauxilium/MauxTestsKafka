package it.mauxilium.MauxKafkaProducer.framework.service;

import it.mauxilium.MauxKafkaProducer.adapter.usecase.TestSessionPerformerAdapter;
import it.mauxilium.MauxKafkaProducer.framework.model.RequestModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SessionExecutorService {

    private TestSessionPerformerAdapter testSessionPerformerAdapter;

    public String sessionSetup(RequestModel requestModel) {
        return testSessionPerformerAdapter.sessionSetup(requestModel.getNumberToSend(), requestModel.getTopic());
    }

    public boolean sessionExecute() {
        try {
            testSessionPerformerAdapter.sessionExecute();
        } catch (RuntimeException ex) {
            log.error("Failure sending message", ex);
            return false;
        }
        return true;
    }
}
