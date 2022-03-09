package it.mauxilium.MauxKafkaProducer.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Slf4j
@Configuration
public class KafkaConfig {

    @Value("${TOPIC_NAME:sample-p1}")
    private String topicName;

    @Value("${TOPIC_PARTITIONS:1}")
    private Integer topicPartitions;

    @Bean
    @Qualifier("DestinationTopicName")
    public String getDestinationTopicName() {
        return topicName;
    }

    @Bean
    public KafkaAdmin.NewTopics topics() {
        NewTopic topicOne = TopicBuilder.name(topicName)
                .partitions(topicPartitions)
                .build();
        log.info("Create topic: {}", topicOne);
        return new KafkaAdmin.NewTopics(topicOne);
    }

}
