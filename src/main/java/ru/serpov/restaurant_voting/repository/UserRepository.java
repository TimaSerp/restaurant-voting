package ru.serpov.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.serpov.restaurant_voting.model.User;

import javax.persistence.QueryHint;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT DISTINCT u FROM User u WHERE u.email=:email")
    @QueryHints({
            @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")
    })
    Optional<User> getByEmail(@Param("email") String email);
}
