package ru.serpov.restaurantvoting.mapper;

import ru.serpov.restaurantvoting.model.dto.CustomDto;
import ru.serpov.restaurantvoting.model.entity.CustomEntity;

import java.util.List;
import java.util.stream.Collectors;

public interface AbstractMapper<DTO extends CustomDto, ENTITY extends CustomEntity> {

    default List<DTO> mapEntities(List<ENTITY> entities) {
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    DTO map(ENTITY entity);

    ENTITY map(DTO dto);
}
