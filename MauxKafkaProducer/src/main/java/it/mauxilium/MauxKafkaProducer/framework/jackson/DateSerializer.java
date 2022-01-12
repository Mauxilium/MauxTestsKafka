package it.mauxilium.MauxKafkaProducer.framework.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateSerializer extends StdSerializer<Date> {

    public DateSerializer() {
        this(null);
    }

    protected DateSerializer(Class<Date> t) {
        super(t);
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = df.format(date);
        log.info("RECEIVED DATE: {}; FORMATTED DATE: {}", date, formattedDate);
        jsonGenerator.writeString(formattedDate);
    }
}
