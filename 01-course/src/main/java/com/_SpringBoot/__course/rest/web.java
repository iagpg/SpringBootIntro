package com._SpringBoot.__course.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
public class web {
    
    @Value("${coach.name}")
    private String coachName;

     @Value("${team.name}")
    private String teamName;


    @GetMapping("/")
    public String hello(){
        return "hi";
    }

    @GetMapping("/props")
    public String getCoachName() {
        return coachName + " "+ teamName; 
    }
    
}
