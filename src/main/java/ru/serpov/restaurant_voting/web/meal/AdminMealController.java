package ru.serpov.restaurant_voting.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.serpov.restaurant_voting.model.Meal;

import java.util.List;

import static ru.serpov.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static ru.serpov.restaurant_voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMealController.REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames = "meals")
public class AdminMealController extends AbstractMealController {
    private static final Logger log = LoggerFactory.getLogger(AdminMealController.class);

    static final String REST_URL = "/admin/restaurants/{restaurant_id}/meals";

    @Override
    @GetMapping
    public List<Meal> getAll(@PathVariable("restaurant_id") int restaurantId) {
        return super.getAll(restaurantId);
    }

    @PostMapping
    @CacheEvict(allEntries = true, cacheNames = "meals")
    public Meal create(@RequestBody Meal meal, @PathVariable("restaurant_id") int restaurantId) {
        log.info("Create {} for restaurant with id {}", meal, restaurantId);
        checkNew(meal);
        return save(meal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restaurant_id") int restaurantId, @PathVariable int id) {
        log.info("Delete meal with id {} for restaurant with id {}", id, restaurantId);
        repository.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true, cacheNames = "meals")
    public void update(@RequestBody Meal meal, @PathVariable("restaurant_id") int restaurantId, @PathVariable int id) {
        log.info("Update {} for restaurant with id {}", meal, restaurantId);
        assureIdConsistent(meal, id);
        save(meal);
    }

    protected Meal save(Meal meal) {
        return repository.save(meal);
    }
}
