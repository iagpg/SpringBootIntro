package com.learnkafka.library_events_producer.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.library_events_producer.domain.LibraryEvent;
import com.learnkafka.library_events_producer.domain.LibraryEventType;
import com.learnkafka.library_events_producer.producer.LibraryEventsProducer;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "${cors.allowed.origin}")
@RestController
public class LibaryEventController {

    @Autowired
    private LibraryEventsProducer libraryEventProducer;


    @PostMapping("/v1/libraryevent")
    @Transactional
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody @Valid LibraryEvent libraryEvent) throws JsonProcessingException {

        libraryEventProducer.sendLibraryEventAsync_approach2(libraryEvent);
        System.out.println(libraryEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
    
     @PutMapping("/v1/libraryevent")
    @Transactional
    public ResponseEntity<?> updateLibraryEvent(@RequestBody @Valid LibraryEvent libraryEvent) throws JsonProcessingException {

        ResponseEntity<String> BAD_REQUEST = validateLibraryEvent(libraryEvent);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        libraryEventProducer.sendLibraryEventAsync_approach2(libraryEvent);
        System.out.println(libraryEvent);
        return ResponseEntity.status(HttpStatus.OK).body(libraryEvent);
    }



    private static ResponseEntity<String> validateLibraryEvent(LibraryEvent libraryEvent){

          if(libraryEvent.libraryEventId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please, pass the LibraryEventId");
        }

        if(!libraryEvent.libraryEventType().equals(LibraryEventType.UPDATE)){
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only UPDATE type is supported");
        }
        return null;
    }
}   


