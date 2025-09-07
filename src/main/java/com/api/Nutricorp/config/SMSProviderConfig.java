package com.api.Nutricorp.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "SMSProvider")
@Getter
public class SMSProviderConfig {
    private String name;
    private String message;
}
