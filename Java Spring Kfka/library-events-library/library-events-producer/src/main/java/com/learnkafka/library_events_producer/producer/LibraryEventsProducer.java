package com.learnkafka.library_events_producer.producer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
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

    public CompletableFuture<SendResult<Integer,String>> sendLibraryEventAsync(LibraryEvent libraryEvent) throws JsonProcessingException {

        var key = libraryEvent.libraryEventId();
        var value = objectMapper.writeValueAsString(libraryEvent);

        var completableFuture = kafkaTemplate.send(topic, key, value);

        return completableFuture.whenComplete((sendResult, throwable) -> {
            if (throwable!= null) {
                handleFailure(key,value, throwable);

            }else{
                handleSucess(key,value,sendResult);

            }
        });
    }

     public CompletableFuture<SendResult<Integer,String>> sendLibraryEventAsync_approach2(LibraryEvent libraryEvent) throws JsonProcessingException {

        var key = libraryEvent.libraryEventId();
        var value = objectMapper.writeValueAsString(libraryEvent);

        var producerRecord =BuildProducerRecord(key,value);
        var completableFuture = kafkaTemplate.send(producerRecord);

        return completableFuture.whenComplete((sendResult, throwable) -> {
            if (throwable!= null) {
                handleFailure(key,value, throwable);

            }else{
                handleSucess(key,value,sendResult);

            }
        });
    }

    
    public void sendLibraryEvent(LibraryEvent libraryEvent) throws InterruptedException, ExecutionException, JsonProcessingException, TimeoutException  {
        
        var key = libraryEvent.libraryEventId();
        var value = objectMapper.writeValueAsString(libraryEvent);
        
        var sendResult = kafkaTemplate.send(topic, key, value).get(3, TimeUnit.SECONDS);
        handleSucess(key, value, sendResult);
    }
    
    private ProducerRecord<Integer,String> BuildProducerRecord(Integer key, String value) {

        List<Header> recordHeaders = List.of(new RecordHeader("event-source","scanner".getBytes()));
        return new ProducerRecord<>( this.topic, null, key, value, recordHeaders);
   }

    public void handleFailure(Integer key, String value, Throwable throwable){

        System.err.println("Error sending the message: " + throwable.getMessage());
        throwable.printStackTrace(System.err);

    }

    public void handleSucess(Integer key, String value, SendResult<Integer,String> sendResult){
        System.out.println(String.format("Message sent successfully for key: %d and value: %s, partition is: %d", key, value, sendResult.getRecordMetadata().partition()));
    }
}