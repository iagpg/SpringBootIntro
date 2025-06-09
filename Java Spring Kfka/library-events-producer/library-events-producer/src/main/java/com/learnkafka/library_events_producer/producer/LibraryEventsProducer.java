package com.learnkafka.library_events_producer.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.library_events_producer.domain.LibraryEvent;

@Component
public class LibraryEventsProducer {

      @Value("${spring.kafka.topic}")
    public String topic;

    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    
    public LibraryEventsProducer(KafkaTemplate<Integer, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public CompletableFuture<SendResult<Integer,String>> sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {

        var key = libraryEvent.libraryEventId();
        var value = objectMapper.writeValueAsString(libraryEvent);

        var completableFuture = kafkaTemplate.send(topic, key, value);

        return completableFuture.whenCompleteAsync((sendResult, throwable) -> {
            if (throwable!= null) {
                handleFailure(key,value, throwable);

            }else{
                handleSucess(key,value,sendResult);

            }
        });
    }
    public void handleFailure(Integer key, String value, Throwable throwable){

        System.err.println("Error sending the message: " + throwable.getMessage());
        throwable.printStackTrace(System.err);

    }

    public void handleSucess(Integer key, String value, SendResult<Integer,String> sendResult){
        System.out.println(String.format("Message sent successfully for key: %d and value: %s, partition is: %d", key, value, sendResult.getRecordMetadata().partition()));
    }
}