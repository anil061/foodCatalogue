package com.learning.foodCatalogue.controller;

import com.learning.foodCatalogue.dto.FoodCataloguePage;
import com.learning.foodCatalogue.dto.FoodItemDTO;
import com.learning.foodCatalogue.entity.FoodItem;
import com.learning.foodCatalogue.service.FoodCatalogueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {

    @Autowired
    FoodCatalogueServices foodCatalogueServices;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO){
        FoodItemDTO saveFoodItem = foodCatalogueServices.addFoodItem(foodItemDTO);
     return new ResponseEntity<>(saveFoodItem, HttpStatus.OK);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchRestauDetailsWithFoodMenu(@PathVariable Integer restaurantId){
        FoodCataloguePage foodCataloguePage = foodCatalogueServices.fetchFoodCataloguePageDetails(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);


    }

}
