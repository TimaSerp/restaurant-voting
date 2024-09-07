package ru.serpov.restaurantvoting.model.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;
import ru.serpov.restaurantvoting.model.dto.NamedDto;

@Data
@EqualsAndHashCode(callSuper = true)
public class MealDto extends NamedDto {
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Range(min = 10, max = 5000)
    private Integer price;

    @Builder
    public MealDto(Integer id, String name, String description, Integer price) {
        super(id, name);
        this.description = description;
        this.price = price;
    }
}
