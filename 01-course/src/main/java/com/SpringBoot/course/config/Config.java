package com.SpringBoot.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.SpringBoot.course.service.SMSNotification;

@Configuration
public class Config {

    @Bean
    public SMSNotification Sms() {
        return new SMSNotification();

    }
}
