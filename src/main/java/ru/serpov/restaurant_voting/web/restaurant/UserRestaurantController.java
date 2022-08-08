package ru.serpov.restaurant_voting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.serpov.restaurant_voting.model.Restaurant;
import ru.serpov.restaurant_voting.model.User;
import ru.serpov.restaurant_voting.model.Vote;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames = "restaurants")
public class UserRestaurantController extends AbstractRestaurantController {
    private static final Logger log = LoggerFactory.getLogger(UserRestaurantController.class);

    static final String REST_URL = "/restaurants";

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

    @PostMapping(value = "/{restaurant_id}/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@RequestBody User user, @PathVariable(name = "restaurant_id") int restaurantId) {
        log.info("{} vote for restaurant with id {}", user, restaurantId);
        repository.vote(user, restaurantId);
    }

    @GetMapping("/vote")
    public Vote getVote(User user) {
        log.info("Get vote for {}", user);
        return repository.getVote(user);
    }

    @GetMapping("/{restaurant_id}/votes")
    public int getVotesCount(@PathVariable("restaurant_id") int restaurantId) {
        log.info("Get votes count for restaurant with id {}", restaurantId);
        return repository.getVoteCount(restaurantId);
    }
}
