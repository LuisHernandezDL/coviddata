package com.myproject.coviddata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoviddataApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoviddataApplication.class, args);
    }
}
