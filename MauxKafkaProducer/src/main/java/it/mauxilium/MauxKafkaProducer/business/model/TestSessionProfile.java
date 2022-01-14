package it.mauxilium.MauxKafkaProducer.business.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class TestSessionProfile {
    int numSampleToSend;
    long delayMillisec;
    String topic;
    long receiverSleepMSec;
}
