package ru.serpov.restaurant_voting.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.serpov.restaurant_voting.error.IllegalRequestDataException;
import ru.serpov.restaurant_voting.model.Meal;

import java.util.List;

import static ru.serpov.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static ru.serpov.restaurant_voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames = "meals")
public class AdminMealController extends AbstractMealController {
    private static final Logger log = LoggerFactory.getLogger(AdminMealController.class);

    static final String REST_URL = "/admin/restaurants/{restaurant_id}/meals";

    @Override
    @GetMapping
    public List<Meal> getAll(@PathVariable("restaurant_id") int restaurantId) {
        return super.getAll(restaurantId);
    }

    @GetMapping
    public Meal get(@RequestParam int id) {
        return repository.findById(id).orElseThrow(() -> new IllegalRequestDataException("Meal with id " + id + " doesn't exist"));
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
    public void delete(@PathVariable int id) {
        log.info("Delete meal with id {}", id);
        repository.deleteExisted(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true, cacheNames = "meals")
    public void update(@RequestBody Meal meal, @PathVariable("restaurant_id") int restaurantId, @PathVariable int id) {
        log.info("Update {} for restaurant with id {}", meal, restaurantId);
        assureIdConsistent(meal, id);
        repository.update(meal.getDescription(), meal.getPrice(), meal.id(), restaurantId);
    }

    protected Meal save(Meal meal) {
        return repository.save(meal);
    }
}
