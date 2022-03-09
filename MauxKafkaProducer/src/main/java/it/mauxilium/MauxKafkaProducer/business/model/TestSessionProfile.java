package it.mauxilium.MauxKafkaProducer.business.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class TestSessionProfile {
    String topic;
    int numSampleToSend;
    long delayMillisec;
    long receiverSleepMSec;
}
