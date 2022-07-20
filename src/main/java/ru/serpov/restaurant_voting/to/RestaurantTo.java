package ru.serpov.restaurant_voting.to;

import ru.serpov.restaurant_voting.model.Meal;

import java.util.List;

public class RestaurantTo {
    private String description;

    private String address;

    private List<Meal> meals;

    private int votes;

    private boolean isBest;
}
