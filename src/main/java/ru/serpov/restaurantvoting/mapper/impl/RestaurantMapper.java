package ru.serpov.restaurantvoting.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serpov.restaurantvoting.mapper.AbstractMapper;
import ru.serpov.restaurantvoting.model.dto.impl.MealDto;
import ru.serpov.restaurantvoting.model.dto.impl.RestaurantDto;
import ru.serpov.restaurantvoting.model.entity.impl.MealEntity;
import ru.serpov.restaurantvoting.model.entity.impl.RestaurantEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends AbstractMapper<RestaurantDto, RestaurantEntity> {

    @Override
    default RestaurantDto map(RestaurantEntity entity) {
        RestaurantDto dto = mapRestaurantToDto(entity);
        dto.setMeals(entity.getMeals() == null
                ? new ArrayList<>()
                : entity.getMeals().stream().map(this::mapMealsToDto).collect(Collectors.toList()));
        return dto;
    }

    @Mapping(target = "meals", ignore = true)
    RestaurantDto mapRestaurantToDto(RestaurantEntity entity);

    MealDto mapMealsToDto(MealEntity entity);

    @Override
    default RestaurantEntity map(RestaurantDto dto) {
        RestaurantEntity entity = mapRestaurantToEntity(dto);
        entity.setMeals(dto.getMeals() == null
                ? new ArrayList<>()
                : dto.getMeals().stream().map(dto1 -> mapMealsToDto(dto1, entity)).collect(Collectors.toList()));
        return entity;
    }

    @Mapping(target = "meals", ignore = true)
    RestaurantEntity mapRestaurantToEntity(RestaurantDto dto);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    MealEntity mapMealsToDto(MealDto dto, RestaurantEntity restaurant);
}