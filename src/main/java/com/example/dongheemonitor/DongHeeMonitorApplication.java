package com.example.dongheemonitor;

import com.example.dongheemonitor.config.GithubApiConfig;
import com.example.dongheemonitor.config.RiotApiConfig;
import com.example.dongheemonitor.config.SlackApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.*;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties({GithubApiConfig.class, RiotApiConfig.class, SlackApiConfig.class})
public class DongHeeMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DongHeeMonitorApplication.class, args);
    }
}
