package ru.serpov.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.serpov.restaurant_voting.model.Meal;

import java.util.List;

@Transactional(readOnly = true)
public interface MealRepository extends BaseRepository<Meal> {

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:id AND m.registered=current_date")
    List<Meal> getAll(int id);
}
