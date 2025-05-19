package com.github.bael.otus.java.hw3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
public class Hw3Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw3Application.class, args);
    }

}
