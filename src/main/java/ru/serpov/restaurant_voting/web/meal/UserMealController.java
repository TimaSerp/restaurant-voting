package ru.serpov.restaurant_voting.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.serpov.restaurant_voting.model.Meal;

import java.util.List;

@RestController
@RequestMapping(value = UserMealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMealController extends AbstractMealController {

    static final String REST_URL = "/restaurants/{restaurant_id}/meals";

    @Override
    @GetMapping
    public List<Meal> getAll(@PathVariable("restaurant_id") int restaurantId) {
        return super.getAll(restaurantId);
    }
}
