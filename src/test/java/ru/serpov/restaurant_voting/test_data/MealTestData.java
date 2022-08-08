package ru.serpov.restaurant_voting.test_data;

import ru.serpov.restaurant_voting.MatcherFactory;
import ru.serpov.restaurant_voting.model.Meal;

import static java.time.LocalDate.now;
import static ru.serpov.restaurant_voting.model.BaseEntity.START_SEQ;
import static ru.serpov.restaurant_voting.test_data.RestaurantTestData.*;

public class MealTestData {
    public static final MatcherFactory.Matcher<Meal> MEAL_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Meal.class, "restaurant.meals");

    public static final int MEAL_1_ID = START_SEQ + 10;
    public static final int MEAL_2_ID = START_SEQ + 11;
    public static final int MEAL_3_ID = START_SEQ + 12;
    public static final int MEAL_4_ID = START_SEQ + 13;
    public static final int NOT_FOUND = 1;

    public static final Meal meal1 = new Meal(MEAL_1_ID, "Цыпленок табака", 500, now(), restaurant1);
    public static final Meal meal2 = new Meal(MEAL_2_ID, "Куриный шашлык", 700, now(), restaurant1);
    public static final Meal meal3 = new Meal(MEAL_3_ID, "Овощи на гриле", 600, now(), restaurant1);
    public static final Meal meal4 = new Meal(MEAL_4_ID, "Компот из сухофруктов", 100, now(), restaurant2);
    public static final Meal meal5 = new Meal(START_SEQ + 14, "Форель запеченная", 500, now(), restaurant2);
    public static final Meal meal6 = new Meal(START_SEQ + 15, "Мясная отбивная", 800, now(), restaurant3);
    public static final Meal meal7 = new Meal(START_SEQ + 16, "Гуляш", 500, now(), restaurant3);
    public static final Meal meal8 = new Meal(START_SEQ + 17, "Ризотто с грибами", 500, now(), restaurant4);
    public static final Meal meal9 = new Meal(START_SEQ + 18, "Смузи из манго", 300, now(), restaurant4);

    public static Meal getNew() {
        return new Meal(null, "Новая еда", 100, now(), restaurant1);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal4);
        updated.setDescription("Updated");
        updated.setPrice(1);
        return updated;
    }
}
