package it.mauxilium.MauxKafkaProducer.framework.model;

import com.fasterxml.jackson.annotation.JsonAlias;
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

    @JsonAlias("sample_index")
    int itemIndex;

    @JsonAlias("stream_size")
    int howToSend;

    String topic;

    @JsonAlias("session_index")
    int sessionId;

    @JsonAlias("sample_timestamp")
    @JsonSerialize(using = DateSerializer.class)
    Date timeStamp;
}
