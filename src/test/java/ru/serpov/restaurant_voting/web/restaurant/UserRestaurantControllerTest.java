package ru.serpov.restaurant_voting.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.serpov.restaurant_voting.model.Vote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.serpov.restaurant_voting.test_data.RestaurantTestData.*;
import static ru.serpov.restaurant_voting.test_data.UserTestData.user;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class UserRestaurantControllerTest {

    @Autowired
    protected UserRestaurantController controller;

    @Test
    void vote() {
        controller.vote(user, RESTAURANT_1_ID);
        Vote vote = controller.getVote(user);
        VOTE_MATCHER.assertMatch(vote, new Vote(vote.getId(), user, restaurant1, vote.getVoteDate()));
    }

    @Test
    void getVote() {
        VOTE_MATCHER.assertMatch(controller.getVote(user), VOTE);
    }

    @Test
    void getVotesCount() {
        assertEquals(controller.getVotesCount(RESTAURANT_2_ID), 2);
    }
}
