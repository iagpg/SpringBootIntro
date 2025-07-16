package com.learnkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


// this component is used to change the default behavior of kafka listener batch to manual offset
//@Component
public class LibraryEventConsumerManulOffset implements AcknowledgingMessageListener<Integer, String> {
    
    @Override
     @KafkaListener(topics = {"libraryEvents"})
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord, @Nullable Acknowledgment acknowledgment) {
          System.out.println("consumer Record: " + consumerRecord);
          // this is the part that acknowledges the message
          acknowledgment.acknowledge();
    }
}
