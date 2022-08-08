package ru.serpov.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.serpov.restaurant_voting.model.Restaurant;
import ru.serpov.restaurant_voting.model.User;
import ru.serpov.restaurant_voting.model.Vote;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Transactional
    @Modifying
    @Query("UPDATE Vote v SET v.user.id=:user_id, v.restaurant.id=:id WHERE v.user.id=:user_id")
    void vote(@Param("user_id") int userId, @Param("id") int id);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:user_id")
    Vote getVote(@Param("user_id") int userId);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.restaurant.id=:id")
    int getVoteCount(@Param("id") int restaurantId);
}
