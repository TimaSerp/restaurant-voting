package ru.serpov.restaurant_voting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.serpov.restaurant_voting.model.Restaurant;

import java.util.List;

import static ru.serpov.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static ru.serpov.restaurant_voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames = "restaurants")
public class AdminRestaurantController extends AbstractRestaurantController {
    private static final Logger log = LoggerFactory.getLogger(AbstractRestaurantController.class);

    static final String REST_URL = "/admin/restaurants";

    @Override
    @GetMapping("/{restaurant_id}")
    public Restaurant get(@PathVariable(name = "restaurant_id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true, cacheNames = "restaurants")
    public Restaurant create(@RequestBody Restaurant restaurant) {
        log.info("Create {}", restaurant);
        checkNew(restaurant);
        return save(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("Delete restaurant with id {}", id);
        repository.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true, cacheNames = "restaurants")
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("Update {} with id {}", restaurant, id);
        assureIdConsistent(restaurant, id);
        save(restaurant);
    }

    protected Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

}
