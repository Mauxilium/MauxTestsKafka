package it.mauxilium.MauxKafkaConsumer.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class KafkaConfig {

    @Value("${TOPIC_NAME:sample-p1}")
    private String topicName;

    @Value("${CONSUMER_GROUP:simple-group}")
    private String consumerGroup;

    @Bean
    @Qualifier("Topic_Name")
    public String getTopicName() {
        return topicName;
    }

    @Bean
    @Qualifier("Consumer_Group")
    public String getConsumerGroup() {
        return consumerGroup;
    }


//    @Bean
//    public KafkaAdmin.NewTopics topics() {
//        NewTopic topicOne = TopicBuilder.name(TopicsDef.TOPIC_ONE_PARTITION)
//                .partitions(1)
//                .build();
//        NewTopic topicTwo = TopicBuilder.name(TopicsDef.TOPIC_TWO_PARTITIONS)
//                .partitions(2)
//                .build();
//        log.info("Create two new topics: {}; {}", topicOne, topicTwo);
//        return new KafkaAdmin.NewTopics(topicOne, topicTwo);
//    }

}
