package com.SpringBoot.course.service;

import org.springframework.stereotype.Component;

import com.SpringBoot.course.abstractClass.Notifications;


@Component
public class GmailNotification implements Notifications    {
      
        @Override
        public String Message () {
           
            return "hi from gmail";
        }
}

