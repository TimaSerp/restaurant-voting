package ru.serpov.restaurant_voting.test_data;

import ru.serpov.restaurant_voting.MatcherFactory;
import ru.serpov.restaurant_voting.model.Restaurant;
import ru.serpov.restaurant_voting.model.Vote;

import java.sql.Date;

import static ru.serpov.restaurant_voting.model.BaseEntity.START_SEQ;
import static ru.serpov.restaurant_voting.test_data.UserTestData.user;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "meals");
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "voteDate", "restaurant.meals", "user.registered");

    public static final int RESTAURANT_1_ID = START_SEQ + 3;
    public static final int RESTAURANT_2_ID = START_SEQ + 4;
    public static final int RESTAURANT_3_ID = START_SEQ + 5;
    public static final int RESTAURANT_4_ID = START_SEQ + 6;
    public static final int NOT_FOUND_RESTAURANT = 2;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_1_ID, "Чайка", "Птичий ресторан", "Улица Пушкина, дом Колотушкина");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_2_ID, "Форель", "Рыбный ресторан", "Окунева авеню, дом 15");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT_3_ID, "Кабанчик", "Мясной ресторан", "Свиной проспект, дом 34");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT_4_ID, "Без мяса", "Веганский ресторан", "Овощной бульвар, дом 0");

    public static final Vote VOTE = new Vote(100007, user, restaurant1, Date.valueOf("2022-08-07"));

    public static Restaurant getNew() {
        return new Restaurant(null, "Новый", "Новый ресторан", "Красная площадь, 1");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(restaurant1);
        updated.setName("Updated");
        updated.setDescription("Updated restaurant");
        updated.setAddress("New address");
        return updated;
    }
}
