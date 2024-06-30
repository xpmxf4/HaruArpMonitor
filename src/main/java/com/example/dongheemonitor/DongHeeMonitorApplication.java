package com.example.dongheemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.*;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableMongoRepositories
public class DongHeeMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DongHeeMonitorApplication.class, args);
    }

}
