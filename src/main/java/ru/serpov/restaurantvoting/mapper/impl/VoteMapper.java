package ru.serpov.restaurantvoting.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serpov.restaurantvoting.mapper.AbstractMapper;
import ru.serpov.restaurantvoting.model.dto.impl.VoteDto;
import ru.serpov.restaurantvoting.model.entity.impl.VoteEntity;

@Mapper(componentModel = "spring")
public interface VoteMapper extends AbstractMapper<VoteDto, VoteEntity> {
    @Override
    @Mapping(target = "userId", source = "entity.user.id")
    @Mapping(target = "restaurantId", source = "entity.restaurant.id")
    VoteDto map(VoteEntity entity);

    @Override
    VoteEntity map(VoteDto dto);
}
