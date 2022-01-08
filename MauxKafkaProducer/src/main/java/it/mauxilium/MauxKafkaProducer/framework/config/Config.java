package it.mauxilium.MauxKafkaProducer.framework.config;

import it.mauxilium.MauxKafkaProducer.adapter.usecase.TestSessionPerformerAdapter;
import it.mauxilium.MauxKafkaProducer.framework.connector.KafkaConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public KafkaConnector getKafkaConnector() {
        return new KafkaConnector();
    }

    @Bean
    public TestSessionPerformerAdapter getPerformerAdapter() {
        return new TestSessionPerformerAdapter(getKafkaConnector());
    }
}
