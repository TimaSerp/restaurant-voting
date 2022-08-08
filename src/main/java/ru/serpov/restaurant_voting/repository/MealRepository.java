package ru.serpov.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.serpov.restaurant_voting.model.Meal;

import java.util.List;

@Transactional(readOnly = true)
public interface MealRepository extends BaseRepository<Meal> {

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:id AND m.registered=current_date")
    List<Meal> getAll(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Meal m SET m.description=:description, m.price=:price, m.restaurant.id=:restaurant_id WHERE m.id=:id")
    void update(@Param("description") String description, @Param("price") int price, @Param("id") int id, @Param("restaurant_id") int restaurantId);
}
