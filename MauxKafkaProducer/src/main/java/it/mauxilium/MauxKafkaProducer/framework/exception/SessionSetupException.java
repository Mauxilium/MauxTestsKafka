package it.mauxilium.MauxKafkaProducer.framework.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class SessionSetupException extends RuntimeException {
    public SessionSetupException(String errorMsg) {
        super(errorMsg);
    }
}



