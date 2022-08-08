package ru.serpov.restaurant_voting.web.restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.serpov.restaurant_voting.error.IllegalRequestDataException;
import ru.serpov.restaurant_voting.model.Restaurant;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.serpov.restaurant_voting.test_data.RestaurantTestData.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class AdminRestaurantControllerTest {

    @Autowired
    protected AdminRestaurantController controller;

    @Test
    public void create() {
        Restaurant created = controller.create(getNew());
        int newId = created.id();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(controller.get(newId), newRestaurant);
    }

    @Test
    void delete() {
        controller.delete(RESTAURANT_1_ID);
        assertThrows(IllegalRequestDataException.class, () -> controller.get(RESTAURANT_1_ID));
    }

    @Test
    void deletedNotFound() {
        Assertions.assertThrows(IllegalRequestDataException.class, () -> controller.delete(NOT_FOUND_RESTAURANT));
    }

    @Test
    void update() {
        Restaurant updated = getUpdated();
        controller.update(updated, updated.id());
        RESTAURANT_MATCHER.assertMatch(controller.get(RESTAURANT_1_ID), getUpdated());
    }

    @Test
    void get() {
        RESTAURANT_MATCHER.assertMatch(controller.get(RESTAURANT_2_ID), restaurant2);
    }

    @Test
    void getNotFound() {
        Assertions.assertThrows(IllegalRequestDataException.class, () -> controller.get(NOT_FOUND_RESTAURANT));
    }

    @Test
    void getAll() {
        List<Restaurant> all = controller.getAll();
        // such order of test-restaurants is to save name ordering
        RESTAURANT_MATCHER.assertMatch(all, restaurant4, restaurant3, restaurant2, restaurant1);
    }
}
