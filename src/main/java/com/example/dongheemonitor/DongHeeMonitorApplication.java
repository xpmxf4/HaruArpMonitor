package com.example.dongheemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.*;

@SpringBootApplication
@EnableMongoRepositories
public class DongHeeMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DongHeeMonitorApplication.class, args);
    }

}
