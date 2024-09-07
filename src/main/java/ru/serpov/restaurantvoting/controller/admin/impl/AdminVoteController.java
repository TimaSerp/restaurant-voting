package ru.serpov.restaurantvoting.controller.admin.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.serpov.restaurantvoting.controller.AbstractController;
import ru.serpov.restaurantvoting.controller.admin.AdminVoteApi;
import ru.serpov.restaurantvoting.model.dto.impl.RestaurantDto;
import ru.serpov.restaurantvoting.service.impl.VoteService;

@Log4j2
@Tag(name = "Admin vote API")
@RestController
@RequestMapping(value = "/api/admin/votes")
@CacheConfig(cacheNames = "votes")
@RequiredArgsConstructor
public class AdminVoteController extends AbstractController<RestaurantDto> implements AdminVoteApi {

    private final VoteService voteService;

    @GetMapping("/restaurant/{id}")
    public int getVotesCount(@PathVariable int id) {
        log.info("Get votes count for restaurant with id {}", id);
        return voteService.getVoteCount(id);
    }
}
