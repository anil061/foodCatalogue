package com.learning.foodCatalogue.service;

import com.learning.foodCatalogue.dto.FoodCataloguePage;
import com.learning.foodCatalogue.dto.FoodItemDTO;
import com.learning.foodCatalogue.dto.Restaurant;
import com.learning.foodCatalogue.entity.FoodItem;
import com.learning.foodCatalogue.mapper.FoodItemMapper;
import com.learning.foodCatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FoodCatalogueServices {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    FoodItemRepo foodItemRepo;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = new FoodItem();
        foodItem.setItemName(foodItemDTO.getItemName());
        foodItem.setItemDescription(foodItemDTO.getItemDescription());
        foodItem.setVeg(foodItemDTO.isVeg());
        foodItem.setPrice((Integer) foodItemDTO.getPrice());
        foodItem.setQuantity(foodItemDTO.getQuantity());
      //  FoodItem savedFoodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        FoodItem savedFoodItem = foodItemRepo.save(foodItem);
      return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(savedFoodItem);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        List<FoodItem> foodItemList =  fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList, restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
