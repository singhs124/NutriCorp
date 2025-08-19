package com.api.Nutricorp.Service;

import com.api.Nutricorp.Model.TokenModel;
import com.api.Nutricorp.config.FoodApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;

@Service
public class FoodApiService {

    private final WebClient webClient;

    @Autowired
    FoodApiConfig foodApiConfig;

    public FoodApiService(){
        this.webClient = WebClient.builder()
                .baseUrl(foodApiConfig.AuthURI)
                .build();
    }

    public String fetchAcessToken(){
        String ceredentials = foodApiConfig.foodApiClientId+":"+foodApiConfig.foodApiClientSecret;
        String basicAuth = Base64.getEncoder().encodeToString(ceredentials.getBytes());
        TokenModel response = webClient.post()
                .uri("/connect/token")
                .header("Authorization" , "Basic " + basicAuth)
                .header("Content-Type" , "application/x-www-form-urlencoded")
                .bodyValue("grant_type=client_credentials&scope=basic")
                .retrieve()
                .bodyToMono(TokenModel.class)
                .block();
        return response != null ? response.getAccessToken() : null;
    }

    public void getFoodList(String item){
        String token = fetchAcessToken();
        System.out.println(token);
    }

    public void getFoodDetails(String item){

    }
}
