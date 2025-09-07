package com.api.Nutricorp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sms-provider")
@Getter
@Setter
public class SMSProviderConfig {
    private String name;
    private String message;
}
