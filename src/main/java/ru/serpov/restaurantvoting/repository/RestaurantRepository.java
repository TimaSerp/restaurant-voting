package ru.serpov.restaurantvoting.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.serpov.restaurantvoting.model.entity.impl.RestaurantEntity;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<RestaurantEntity> {
}
