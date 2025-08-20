package com.api.Nutricorp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final FoodApiConfig foodApiConfig;

    public WebClientConfig(FoodApiConfig foodApiConfig){
        this.foodApiConfig = foodApiConfig;
    }

    @Bean
    @Qualifier("authWebClient")
    public WebClient authWebClient(){
        return WebClient.builder()
                .baseUrl(foodApiConfig.getAuthUri())
                .build();
    }

    @Bean
    @Qualifier("apiWebClient")
    public  WebClient apiWebClient(){
        return WebClient.builder()
                .baseUrl(foodApiConfig.getBaseUri())
                .build();
    }
}
