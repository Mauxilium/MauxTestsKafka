package it.mauxilium.MauxKafkaProducer.framework.model;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@ToString
@AllArgsConstructor
public class RequestModel {

    @NotNull
    int streamSize;

    @NotNull
    long delayMillisec;

    @NotBlank
    String topic;
}
