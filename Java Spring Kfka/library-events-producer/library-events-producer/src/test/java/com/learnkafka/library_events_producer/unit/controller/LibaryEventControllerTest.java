package com.learnkafka.library_events_producer.unit.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.library_events_producer.Controller.LibaryEventController;
import com.learnkafka.library_events_producer.domain.LibraryEvent;
import com.learnkafka.library_events_producer.producer.LibraryEventsProducer;
import com.learnkafka.library_events_producer.utils.TestUtils;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@WebMvcTest(LibaryEventController.class)
public class LibaryEventControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    LibraryEventsProducer libraryEventsProducer;

    @Test
    void testPostLibraryEvent() throws Exception {

        var json = objectMapper.writeValueAsString(TestUtils.libraryEventRecord());
        
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/libraryevent").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
        
        when(libraryEventsProducer.sendLibraryEventAsync_approach2(isA(LibraryEvent.class))).thenReturn(null);

    }

    @Test
      void testPostLibraryEvent_invalidValues() throws Exception {

        var json = objectMapper.writeValueAsString(TestUtils.libraryEventRecordWithInvalidBook());
        
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/libraryevent").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
        
        when(libraryEventsProducer.sendLibraryEventAsync_approach2(isA(LibraryEvent.class))).thenReturn(null);
        
    }
}
