package ru.serpov.restaurantvoting.controller.admin.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.serpov.restaurantvoting.controller.AbstractController;
import ru.serpov.restaurantvoting.controller.admin.AdminRestaurantApi;
import ru.serpov.restaurantvoting.model.dto.impl.RestaurantDto;

@Log4j2
@RestController
@RequestMapping(value = "/api/admin/restaurants")
@CacheConfig(cacheNames = "restaurants")
@RequiredArgsConstructor
public class AdminRestaurantController extends AbstractController<RestaurantDto> implements AdminRestaurantApi {

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true, cacheNames = "restaurants")
    public ResponseEntity<RestaurantDto> create(@RequestBody RestaurantDto restaurant) {
        return createNew(restaurant);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        deleteById(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true, cacheNames = "restaurants")
    public ResponseEntity<RestaurantDto> update(@RequestBody RestaurantDto restaurant, @PathVariable int id) {
        return updateExisted(restaurant, id);
    }
}
