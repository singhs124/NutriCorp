package com.api.Nutricorp.Controller;

import com.api.Nutricorp.Service.FoodApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food/v1")
public class FoodApiController {
    @Autowired
    FoodApiService foodApiService;

    @GetMapping("/")
    public void searchFood() throws JsonProcessingException {
        foodApiService.getFoodList("abc");
    }
}
