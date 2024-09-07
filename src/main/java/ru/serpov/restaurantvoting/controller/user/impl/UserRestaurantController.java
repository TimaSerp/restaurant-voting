package ru.serpov.restaurantvoting.controller.user.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.serpov.restaurantvoting.controller.AbstractController;
import ru.serpov.restaurantvoting.controller.user.UserRestaurantApi;
import ru.serpov.restaurantvoting.model.dto.impl.RestaurantDto;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames = "restaurants")
@RequiredArgsConstructor
public class UserRestaurantController extends AbstractController<RestaurantDto> implements UserRestaurantApi {

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> get(@PathVariable int id) {
        return getById(id);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAll() {
        return getAllDtos();
    }
}
