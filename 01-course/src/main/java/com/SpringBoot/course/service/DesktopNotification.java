package com.SpringBoot.course.service;

import org.springframework.stereotype.Component;

import com.SpringBoot.course.abstractClass.Notifications;


@Component
public class DesktopNotification implements Notifications    {

    public DesktopNotification() {
        System.out.println("in constructor" + getClass().getSimpleName()); 
    }

    @Override
    public String Message () {
        return "hi from desktop";
    }
}

