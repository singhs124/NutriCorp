package com.api.Nutricorp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fatsecret")
@Getter
@Setter
public class FoodApiConfig {
    private String clientId;
    private String clientSecret;
    private String BaseUri;
    private String AuthUri;
}
