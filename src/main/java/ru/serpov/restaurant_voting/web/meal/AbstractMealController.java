package ru.serpov.restaurant_voting.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serpov.restaurant_voting.model.Meal;
import ru.serpov.restaurant_voting.repository.MealRepository;

import java.util.List;

public abstract class AbstractMealController {
    private static final Logger log = LoggerFactory.getLogger(AbstractMealController.class);

    @Autowired
    protected MealRepository repository;

    public List<Meal> getAll(int restaurantId) {
        log.info("Get All for restaurant with id {}", restaurantId);
        return repository.getAll(restaurantId);
    }
}
