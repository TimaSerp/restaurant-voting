package ru.serpov.restaurantvoting.model.dto.impl;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.serpov.restaurantvoting.model.HasId;
import ru.serpov.restaurantvoting.model.dto.NamedDto;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RestaurantDto extends NamedDto implements HasId {

    @NotBlank
    private String description;

    @NotBlank
    private String address;

    private List<MealDto> meals;

    @Builder
    public RestaurantDto(Integer id, String name, String description, String address, List<MealDto> meals) {
        super(id, name);
        this.description = description;
        this.address = address;
        this.meals = meals;
    }
}
