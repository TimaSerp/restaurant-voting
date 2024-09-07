package ru.serpov.restaurantvoting.mapper.impl;

import org.mapstruct.Mapper;
import ru.serpov.restaurantvoting.mapper.AbstractMapper;
import ru.serpov.restaurantvoting.model.dto.impl.UserDto;
import ru.serpov.restaurantvoting.model.entity.impl.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper extends AbstractMapper<UserDto, UserEntity> {
    @Override
    UserEntity map(UserDto dto);

    @Override
    UserDto map(UserEntity entity);
}
