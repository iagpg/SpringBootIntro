package com.learnkafka.library_events_producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AutoCreateConfig {
    
    @Value("${spring.kafka.topic}")
    public String topic;

    @Bean
    public NewTopic libraryEvents() {
        return TopicBuilder
            .name(topic)
            .partitions(3)
            .replicas(3)
            .build();
    }
}
