package com.learning.foodCatalogue.mapper;

import com.learning.foodCatalogue.dto.FoodItemDTO;
import com.learning.foodCatalogue.entity.FoodItem;
import com.learning.foodCatalogue.repo.FoodItemRepo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {

    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO foodItemDTO);

    FoodItemDTO mapFoodItemToFoodItemDto(FoodItem foodItem);
}
