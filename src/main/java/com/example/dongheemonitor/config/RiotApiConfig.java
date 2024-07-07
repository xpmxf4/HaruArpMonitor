package com.example.dongheemonitor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "riot")
public class RiotApiConfig {

    private String apiKey;
    private String dongheePuuid;
    private String baseUrl;
}
