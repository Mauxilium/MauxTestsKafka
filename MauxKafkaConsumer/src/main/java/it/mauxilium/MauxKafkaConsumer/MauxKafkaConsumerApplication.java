package it.mauxilium.MauxKafkaConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = "it.mauxilium.MauxKafkaConsumer.framework")
public class MauxKafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MauxKafkaConsumerApplication.class, args);
    }

}
