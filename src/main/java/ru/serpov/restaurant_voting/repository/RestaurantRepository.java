package ru.serpov.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.serpov.restaurant_voting.model.Restaurant;
import ru.serpov.restaurant_voting.model.User;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Modifying
    @Query("UPDATE Vote v SET v.user=:user, v.restaurant.id=:id WHERE v.user=:user")
    void vote(User user, int id);
}
