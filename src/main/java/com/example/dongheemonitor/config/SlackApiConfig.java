package com.example.dongheemonitor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "slack")
public class SlackApiConfig {
    private String signingSecret;
    private String botToken;
    private String appToken;
    private String dongheeId;
}
