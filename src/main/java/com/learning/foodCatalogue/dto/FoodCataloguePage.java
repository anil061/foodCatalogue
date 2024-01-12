package com.learning.foodCatalogue.dto;

import com.learning.foodCatalogue.entity.FoodItem;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCataloguePage {
    private List<FoodItem> foodItemList;
    private Restaurant restaurant;

}
