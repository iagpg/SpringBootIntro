package com.SpringBoot.course.rest;

import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.course.abstractClass.Notifications;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;







@RestController
public class Web {

    @Autowired
    @Qualifier("desktopNotification")
    private Notifications notifications;
    
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

    @GetMapping("/message")
    public String getMethodName() {
        return notifications.Message();
    }
       
    
}
