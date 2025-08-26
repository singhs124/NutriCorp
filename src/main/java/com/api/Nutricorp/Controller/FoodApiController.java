package com.api.Nutricorp.Controller;

import com.api.Nutricorp.Service.FoodApiService;
import com.api.Nutricorp.dto.ApiResponse;
import com.api.Nutricorp.dto.Food;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food/v1")
public class FoodApiController {
    @Autowired
    FoodApiService foodApiService;

    @GetMapping("/getFoods")
    public ResponseEntity<ApiResponse<List<Food>>> searchFood(@RequestParam String foodItem) throws JsonProcessingException {
        List<Food> foods = foodApiService.getFoodList(foodItem);
        return new ResponseEntity<>(new ApiResponse<List<Food>>(true,foods), HttpStatusCode.valueOf(200));
    }

}
