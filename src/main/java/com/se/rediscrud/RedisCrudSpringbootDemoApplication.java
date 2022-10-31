package com.se.rediscrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisCrudSpringbootDemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RedisCrudSpringbootDemoApplication.class, args);
    }
    
}
