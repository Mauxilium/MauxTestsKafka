package it.mauxilium.MauxKafkaConsumer.framework.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateDeserializer extends StdDeserializer<Date> {

    public DateDeserializer() {
        this(null);
    }

    public DateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            log.debug("Try to deserialize received date: {}", jsonParser.getText());
            return df.parse(jsonParser.getText());
        } catch (ParseException e) {
            log.debug("Deserialization error: {}; {}", e, e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
