package ru.serpov.restaurant_voting.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.serpov.restaurant_voting.error.IllegalRequestDataException;
import ru.serpov.restaurant_voting.model.Role;
import ru.serpov.restaurant_voting.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.serpov.restaurant_voting.test_data.UserTestData.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class AdminUserControllerTest {

    @Autowired
    protected AdminUserController controller;

    @Test
    public void create() {
        User created = controller.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(controller.get(newId), newUser);
    }

    @Test
    void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                controller.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    void delete() {
        controller.delete(USER_ID);
        assertThrows(IllegalRequestDataException.class, () -> controller.get(USER_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(IllegalRequestDataException.class, () -> controller.delete(NOT_FOUND));
    }

    @Test
    void get() {
        USER_MATCHER.assertMatch(controller.get(ADMIN_ID), admin);
    }

    @Test
    void getNotFound() {
        assertThrows(IllegalRequestDataException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    void getByEmail() {
        User user = controller.getByEmail("admin@gmail.com").orElse(null);
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    void update() {
        User updated = getUpdated();
        controller.update(updated, updated.id());
        USER_MATCHER.assertMatch(controller.get(USER_ID), getUpdated());
    }

    @Test
    void getAll() {
        List<User> all = controller.getAll();
        USER_MATCHER.assertMatch(all, admin, guest, user);
    }
}