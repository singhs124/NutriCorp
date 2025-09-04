package com.api.Nutricorp.Service;

import com.api.Nutricorp.Model.TokenModel;
import com.api.Nutricorp.config.FoodApiConfig;
import com.api.Nutricorp.config.WebClientConfig;
import com.api.Nutricorp.dto.Food;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class FoodApiService {

    private final WebClient authWebClient;
    private final WebClient apiWebClient;
    private final FoodApiConfig foodApiConfig;
    private String access_token;

    @Autowired
    public FoodApiService(FoodApiConfig foodApiConfig,
                          @Qualifier("authWebClient") WebClient authWebClient,
                          @Qualifier("apiWebClient") WebClient apiWebClient){
        this.foodApiConfig = foodApiConfig ;
        this.authWebClient = authWebClient;
        this.apiWebClient = apiWebClient;
//        fetchAccessToken();
    }

    private void fetchAccessToken(){
        try {
            String credentials = foodApiConfig.getClientId() + ":" + foodApiConfig.getClientSecret();
            String basicAuth = Base64.getEncoder().encodeToString(credentials.getBytes());
            ResponseEntity<String> response = authWebClient.post()
                    .uri("/connect/token")
                    .header("Authorization", "Basic " + basicAuth)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                            .with("scope", "basic"))
                    .retrieve()
                    .toEntity(String.class)
                    .block();
            if (response == null || !response.hasBody()) {
                System.out.println("No response or empty Body Received");
                return;
            }
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);
            access_token = rootNode.get("access_token").asText();
            System.out.println("<--------------->");
            System.out.println("Token Fetched Successfully!");
            System.out.println("<--------------->");
        } catch (WebClientResponseException e){
            System.out.println("HTTP Error: Status " + e.getRawStatusCode() + "-" + e.getResponseBodyAsString());
        } catch (WebClientRequestException e){
            System.out.println("Network Error " + e.getMessage());
        } catch (JsonProcessingException e){
            System.out.println("Error Parsing Json Response " + e.getMessage());
        } catch (Exception e){
            System.out.println("Unexpected Error " + e.getMessage());
        }
    }

    public List<Food> getFoodList(String item) throws JsonProcessingException {
        List<Food> result = new ArrayList<>();
        ResponseEntity<String> response = apiWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/foods/search/v1")
                        .queryParam("search_expression" , item)
                        .queryParam("format" , "json")
                        .queryParam("max_results" , 10)
                        .build())
                .header("Authorization" , "Bearer " + access_token)
                .retrieve()
                .toEntity(String.class)
                .block();
        if(response == null || !response.hasBody()){
            System.out.println("No Response or empty Response Body");
            return result;
        }
        String responseBody = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseBody);
        JsonNode foodArray = root.path("foods").path("food");
        if(foodArray.isArray()){
            for(JsonNode food: foodArray){
                String foodType = food.path("food_type").asText();
                String foodName = food.path("food_name").asText();
                String foodId = food.path("food_id").asText();
                String desc = food.path("food_description").asText();
                Food foodObject = new Food(foodName,foodId,desc);
                System.out.println(foodType + "-->" + foodName);
                result.add(foodObject);
            }
        }
        return result;
    }

    public void getFoodDetails(String item){

    }
}
