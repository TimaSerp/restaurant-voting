package ru.serpov.restaurantvoting.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.serpov.restaurantvoting.model.dto.impl.RestaurantDto;
import ru.serpov.restaurantvoting.model.entity.impl.RestaurantEntity;
import ru.serpov.restaurantvoting.service.AbstractService;

@Log4j2
@Service
@RequiredArgsConstructor
public class RestaurantService extends AbstractService<RestaurantDto, RestaurantEntity> {
}
