package ru.serpov.restaurantvoting.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serpov.restaurantvoting.error.BadRequestException;
import ru.serpov.restaurantvoting.mapper.impl.VoteMapper;
import ru.serpov.restaurantvoting.model.dto.impl.VoteDto;
import ru.serpov.restaurantvoting.model.entity.impl.VoteEntity;
import ru.serpov.restaurantvoting.repository.RestaurantRepository;
import ru.serpov.restaurantvoting.repository.UserRepository;
import ru.serpov.restaurantvoting.repository.VoteRepository;
import ru.serpov.restaurantvoting.service.AbstractService;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;

@Log4j2
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VoteService extends AbstractService<VoteDto, VoteEntity> {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;

    @Transactional
    public void vote(int userId, int restaurantId) {
        if (LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            log.error("Cannot vote after 11:00 am");
            throw new BadRequestException("Cannot vote after 11:00 am");
        }
        VoteEntity todayVote = voteRepository.getTodayVote(userId);
        VoteEntity vote = VoteEntity.builder()
                .id(todayVote == null ? null : todayVote.getId())
                .user(todayVote == null ? userRepository.getReferenceById(userId) : todayVote.getUser())
                .restaurant(restaurantRepository.getReferenceById(restaurantId))
                .voteDate(Date.from(Instant.now()))
                .build();
        voteRepository.save(vote);
    }

    @Transactional
    public void unvote(int userId) {
        voteRepository.deleteByUserId(userId);
    }

    public VoteDto getVote(int userId) {
        VoteEntity todayVote = voteRepository.getTodayVote(userId);
        return voteMapper.map(todayVote);
    }

    public int getVoteCount(int restaurantId) {
        return voteRepository.getVoteCount(restaurantId);
    }
}
