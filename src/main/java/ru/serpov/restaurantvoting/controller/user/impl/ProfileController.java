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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.serpov.restaurantvoting.controller.AbstractController;
import ru.serpov.restaurantvoting.controller.user.ProfileApi;
import ru.serpov.restaurantvoting.model.AuthUser;
import ru.serpov.restaurantvoting.model.dto.impl.UserDto;

import static ru.serpov.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.serpov.restaurantvoting.util.ValidationUtil.checkNew;

@Log4j2
@RestController
@RequestMapping(value = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames = "users")
@RequiredArgsConstructor
public class ProfileController extends AbstractController<UserDto> implements ProfileApi {

    @Override
    @GetMapping
    public ResponseEntity<UserDto> get(@AuthenticationPrincipal AuthUser authUser) {
        return getById(authUser.id());
    }

    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser) {
        super.deleteById(authUser.id());
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<UserDto> register(@RequestBody UserDto user) {
        log.info("Register {}", user);
        checkNew(user);
        return createNew(user);
    }

    @Override
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user, @AuthenticationPrincipal AuthUser authUser) {
        int id = authUser.id();
        log.info("Update {} with id {}", user, id);
        assureIdConsistent(user, id);
        return updateExisted(user, user.getId());
    }
}
