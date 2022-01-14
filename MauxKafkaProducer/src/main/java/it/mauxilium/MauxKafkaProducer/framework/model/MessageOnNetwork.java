package it.mauxilium.MauxKafkaProducer.framework.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.mauxilium.MauxKafkaProducer.framework.jackson.DateSerializer;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

import java.util.Date;

@Value
@ToString
@AllArgsConstructor
public class MessageOnNetwork {

    @JsonProperty("sample_index")
    int itemIndex;

    @JsonProperty("stream_size")
    int howToSend;

    String topic;

    @JsonProperty("session_index")
    int sessionId;

    @JsonProperty("sample_timestamp")
    @JsonSerialize(using = DateSerializer.class)
    Date timeStamp;

    @JsonProperty("receiver_sleep")
    long receiverSleepMSec;
}
