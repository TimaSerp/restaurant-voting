package ru.serpov.restaurant_voting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import ru.serpov.restaurant_voting.error.IllegalRequestDataException;
import ru.serpov.restaurant_voting.model.Restaurant;
import ru.serpov.restaurant_voting.repository.RestaurantRepository;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

public abstract class AbstractRestaurantController {
    private static final Logger log = LoggerFactory.getLogger(AbstractRestaurantController.class);

    @Autowired
    protected RestaurantRepository repository;

    public Restaurant get(int id) {
        log.info("Get restaurant with id {}", id);
        return repository.findById(id).orElseThrow(() -> new IllegalRequestDataException("Restaurant with id " + id + " doesn't exist"));
    }

    public List<Restaurant> getAll() {
        log.info("Get all restaurants");
        return repository.findAll(Sort.by(ASC, "name", "id"));
    }
}
