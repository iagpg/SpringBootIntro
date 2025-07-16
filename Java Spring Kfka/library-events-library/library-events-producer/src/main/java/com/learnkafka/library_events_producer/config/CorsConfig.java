/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.learnkafka.library_events_producer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 *
 * @author iago
 */

 
@Configuration
public class CorsConfig {

    
    private static final Logger log = LoggerFactory.getLogger(CorsConfig.class);

    @Value("${cors.allowed.origin}")
    private String allowedOrigin;

 
    @Bean
    public WebMvcConfigurer corsConfigurer() {
         return new WebMvcConfigurer() {
             @Override
            public void addCorsMappings(@org.springframework.lang.NonNull CorsRegistry registry) {
                 log.info("CORS allowed origin: {}", allowedOrigin);
                 
                registry.addMapping("/**") // aplica a todos os endpoints
                        .allowedOrigins(allowedOrigin) // ou "*", mas evite em produção
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
                        //.allowCredentials(true); // se for usar cookies/autenticação
            }
        };
       
    }
}
