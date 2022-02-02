package it.mauxilium.MauxKafkaConsumer.framework.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.mauxilium.MauxKafkaConsumer.framework.jackson.DateDeserializer;
import it.mauxilium.MauxKafkaConsumer.framework.jackson.DateSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class MessageOnNetwork {

    @JsonAlias("sample_index")
    private int itemIndex;

    @JsonAlias("stream_size")
    private int howToSend;

    private String topic;

    @JsonAlias("session_index")
    private int sessionId;

    @JsonAlias("sample_timestamp")
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private Date timeStamp;

    @JsonAlias("receiver_sleep")
    private long receiverSleepMSec;
}
