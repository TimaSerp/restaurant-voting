package ru.serpov.restaurantvoting.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public abstract class NamedDto extends CustomDto {
    @NotBlank
    @Size(min = 2, max = 128)
    protected String name;

    public NamedDto(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
