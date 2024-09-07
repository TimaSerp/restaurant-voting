package ru.serpov.restaurantvoting.controller.user.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.serpov.restaurantvoting.controller.AbstractController;
import ru.serpov.restaurantvoting.controller.user.UserVoteApi;
import ru.serpov.restaurantvoting.model.AuthUser;
import ru.serpov.restaurantvoting.model.dto.impl.RestaurantDto;
import ru.serpov.restaurantvoting.model.dto.impl.VoteDto;
import ru.serpov.restaurantvoting.service.impl.VoteService;

@Log4j2
@RestController
@RequestMapping(value = "/api/votes", produces = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames = "votes")
@RequiredArgsConstructor
public class UserVoteController extends AbstractController<RestaurantDto> implements UserVoteApi {

    private final VoteService voteService;

    @Override
    @PostMapping(value = "/restaurant/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true, cacheNames = "votes")
    public void vote(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        int userId = authUser.id();
        log.info("User with id {} want to vote for restaurant with id {}", userId, id);
        voteService.vote(userId, id);
        log.info("User with id {} voted for restaurant with id {}", userId, id);
    }

    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unvote(@AuthenticationPrincipal AuthUser authUser) {
        int userId = authUser.id();
        log.info("User with id {} want to unvote", userId);
        voteService.unvote(userId);
        log.info("User with id {} unvoted", userId);
    }

    @Override
    @GetMapping
    public ResponseEntity<VoteDto> getVote(@AuthenticationPrincipal AuthUser authUser) {
        int userId = authUser.id();
        log.info("Get vote for user with id {}", userId);
        return ResponseEntity.ok(voteService.getVote(userId));
    }
}
