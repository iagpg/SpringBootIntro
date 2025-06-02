package com._course.rest.restController;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class demoRestController {
    
    @GetMapping("/hello")
    public String getMethodName() {
        return "hello World";
    }
    
}
