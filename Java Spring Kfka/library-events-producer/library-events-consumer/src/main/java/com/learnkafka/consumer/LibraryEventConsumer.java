package com.learnkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LibraryEventConsumer {
    
    @KafkaListener(topics = {"libraryEvents"})
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord){
    
        System.out.println("consumer Record");
        System.out.println(consumerRecord);
    }
}
