package it.mauxilium.MauxKafkaConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("it.mauxilium.MauxKafkaProducer.framework")
public class MauxKafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MauxKafkaConsumerApplication.class, args);
    }

}
