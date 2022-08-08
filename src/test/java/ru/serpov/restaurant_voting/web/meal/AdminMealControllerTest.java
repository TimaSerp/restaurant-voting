package ru.serpov.restaurant_voting.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.serpov.restaurant_voting.error.IllegalRequestDataException;
import ru.serpov.restaurant_voting.model.Meal;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.serpov.restaurant_voting.test_data.MealTestData.*;
import static ru.serpov.restaurant_voting.test_data.RestaurantTestData.RESTAURANT_1_ID;
import static ru.serpov.restaurant_voting.test_data.RestaurantTestData.RESTAURANT_2_ID;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class AdminMealControllerTest {

    @Autowired
    protected AdminMealController controller;

    @Test
    public void create() {
        Meal created = controller.create(getNew(), RESTAURANT_1_ID);
        int newId = created.id();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(controller.get(newId), newMeal);
    }

    @Test
    void delete() {
        controller.delete(MEAL_1_ID);
        assertThrows(IllegalRequestDataException.class, () -> controller.get(MEAL_1_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(IllegalRequestDataException.class, () -> controller.delete(NOT_FOUND));
    }

    @Test
    void get() {
        MEAL_MATCHER.assertMatch(controller.get(MEAL_1_ID), meal1);
    }

    @Test
    void getNotFound() {
        assertThrows(IllegalRequestDataException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    void update() {
        Meal updated = getUpdated();
        controller.update(updated, RESTAURANT_2_ID, updated.id());
        MEAL_MATCHER.assertMatch(controller.get(MEAL_4_ID), getUpdated());
    }

    @Test
    void getAll() {
        List<Meal> all = controller.getAll(RESTAURANT_1_ID);
        MEAL_MATCHER.assertMatch(all, meal1, meal2, meal3);
    }
}
