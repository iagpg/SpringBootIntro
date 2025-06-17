package com.learnkafka.library_events_producer.Controller;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
@ControllerAdvice
public class LibraryEventControllerAdvice {
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException ex){

        var errorMessage = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage()).sorted().collect(Collectors.joining(","));
        System.err.println(errorMessage);
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
}
