package ru.serpov.restaurantvoting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.serpov.restaurantvoting.model.entity.impl.VoteEntity;

@Repository
public interface VoteRepository extends BaseRepository<VoteEntity> {
    @Query("SELECT v FROM VoteEntity v WHERE v.user.id=:user_id AND v.voteDate=current_date")
    VoteEntity getTodayVote(@Param("user_id") int userId);

    @Query("SELECT COUNT(v) FROM VoteEntity v WHERE v.restaurant.id=:id")
    int getVoteCount(@Param("id") int restaurantId);

    void deleteByUserId(int userId);
}
