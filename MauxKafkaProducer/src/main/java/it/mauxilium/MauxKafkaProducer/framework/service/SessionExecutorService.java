package it.mauxilium.MauxKafkaProducer.framework.service;

import it.mauxilium.MauxKafkaProducer.adapter.usecase.TestSessionPerformerAdapter;
import it.mauxilium.MauxKafkaProducer.business.model.SetupStatusResult;
import it.mauxilium.MauxKafkaProducer.business.model.TopicsDef;
import it.mauxilium.MauxKafkaProducer.framework.connector.KafkaConnector;
import it.mauxilium.MauxKafkaProducer.framework.exception.SessionSetupException;
import it.mauxilium.MauxKafkaProducer.framework.model.RequestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class SessionExecutorService {

    private static final String SETUP_OK_MSG = "Test Setup successfully done";
    private static final String SETUP_INVALID_STREAM_SIZE_MSG = "Invalid number of messages to send, must be > 0, found: %d";
    private static final String SETUP_INVALID_DELAY_TIME_MSG = "Invalid delay between messages, must be > 0, found: %d";
    private static final String SETUP_FAILS_EMPTY_TOPIC_MSG = "Invalid empty destination topic";
    private static final String SETUP_FAILS_INVALID_TOPIC_NAME_MSG = "Invalid destination topic name: %s";
    private static final String SETUP_FAILS_UNEXPECTED_TOPIC_NAME_MSG = "Invalid destination topic name: %s; is not one of the expected [%s, %s]";
    private static final String SETUP_UNKNOW_RESULT_STATUS = "Unknow setup status: %s";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private TestSessionPerformerAdapter testSessionPerformerAdapter;

    @PostConstruct
    public void setup() {
        KafkaConnector connector = new KafkaConnector(kafkaTemplate);
        testSessionPerformerAdapter = new TestSessionPerformerAdapter(connector);
    }

    public String sessionSetup(RequestModel requestModel) {
        SetupStatusResult response = testSessionPerformerAdapter.sessionSetup(
                requestModel.getStreamSize(),
                requestModel.getDelayMillisec(),
                requestModel.getTopic(),
                requestModel.getReceiverSleep());

        return evaluateResponse(requestModel, response);
    }

    private String evaluateResponse(RequestModel requestModel, SetupStatusResult response) {
        if (response == SetupStatusResult.OK) {
            return "Done";
        } else {
            String errorMsg;
            switch (response) {
                case INVALID_STREAM_SIZE:
                    errorMsg = String.format(SETUP_INVALID_STREAM_SIZE_MSG, requestModel.getStreamSize());
                    break;
                case INVALID_DELAY_VALUE:
                    errorMsg = String.format(SETUP_INVALID_DELAY_TIME_MSG, requestModel.getDelayMillisec());
                    break;
                case INVALID_EMPTY_TOPIC_NAME:
                    errorMsg = SETUP_FAILS_EMPTY_TOPIC_MSG;
                    break;
                case INVALID_TOPIC_NAME:
                    errorMsg = String.format(SETUP_FAILS_INVALID_TOPIC_NAME_MSG, requestModel.getTopic());
                    break;
                case UNEXPECTED_TOPIC_NAME:
                    errorMsg = String.format(SETUP_FAILS_UNEXPECTED_TOPIC_NAME_MSG, requestModel.getTopic(), TopicsDef.TOPIC_ONE_PARTITION, TopicsDef.TOPIC_TWO_PARTITIONS);
                    break;
                default:
                    errorMsg = String.format(SETUP_UNKNOW_RESULT_STATUS, response);
            }
            throw new SessionSetupException(errorMsg);
        }
    }

    public String sessionExecute() {
        try {
            testSessionPerformerAdapter.sessionExecute();
        } catch (RuntimeException ex) {
            log.error("Failure sending message", ex);
            return ex.toString();
        }
        return "Done";
    }
}
