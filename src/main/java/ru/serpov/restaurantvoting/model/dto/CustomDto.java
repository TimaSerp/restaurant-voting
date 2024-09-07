package ru.serpov.restaurantvoting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.serpov.restaurantvoting.model.HasId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class CustomDto implements HasId {
    public Integer id;
}
