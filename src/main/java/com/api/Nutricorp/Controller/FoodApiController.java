package com.api.Nutricorp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food/v1")
public class FoodApiController {

    @GetMapping("/")
    public void searchFood(String item){
        searchFood(item);
    }
}
