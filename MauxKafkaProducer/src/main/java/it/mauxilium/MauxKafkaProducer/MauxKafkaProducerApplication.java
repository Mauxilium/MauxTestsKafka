package it.mauxilium.MauxKafkaProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("it.mauxilium.MauxKafkaProducer.framework")
public class MauxKafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MauxKafkaProducerApplication.class, args);
	}

}
