package com.learnkafka.library_events_producer.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.learnkafka.library_events_producer.domain.LibraryEvent;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class LibaryEventController {


    private KafkaTemplate kafkaTemplate;

    @PostMapping("/v1/libraryevent")
    @Transactional
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) {

        //TODO invoke the kafka producer
        System.out.println(libraryEvent);

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
        
}   
