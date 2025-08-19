package com.api.Nutricorp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FoodApiConfig {
    @Value("${fatsecret.clientId}")
    public String foodApiClientId;

    @Value("${fatsecret.clientSecret}")
    public String foodApiClientSecret;

    @Value("${fatsecret.Uri}")
    public String BaseURI;

    @Value("${fatsecret.AuthUri}")
    public String AuthURI;
}
