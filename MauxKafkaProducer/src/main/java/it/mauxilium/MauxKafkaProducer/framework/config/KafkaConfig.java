package it.mauxilium.MauxKafkaProducer.framework.config;

import it.mauxilium.MauxKafkaProducer.business.model.TopicsDef;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Slf4j
@Configuration
public class KafkaConfig {

    @Bean
    public KafkaAdmin.NewTopics topics() {
        NewTopic topicOne = TopicBuilder.name(TopicsDef.TOPIC_ONE_PARTITION)
                .partitions(1)
                .build();
        NewTopic topicTwo = TopicBuilder.name(TopicsDef.TOPIC_TWO_PARTITIONS)
                .partitions(2)
                .build();
        log.info("Create two new topics: {}; {}", topicOne, topicTwo);
        return new KafkaAdmin.NewTopics(topicOne, topicTwo);
    }

}
