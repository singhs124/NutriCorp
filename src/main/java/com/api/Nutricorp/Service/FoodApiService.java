package com.api.Nutricorp.Service;

import com.api.Nutricorp.config.FoodApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class FoodApiService {
    @Autowired
    FoodApiConfig foodApiConfig;


    public void getFoodList(String item){


    }

    public void getFoodDetails(String item){

    }
}
