package ru.serpov.restaurant_voting.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.serpov.restaurant_voting.model.User;

import static ru.serpov.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static ru.serpov.restaurant_voting.util.ValidationUtil.checkNew;
import static ru.serpov.restaurant_voting.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(name = ProfileController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames = "users")
public class ProfileController extends AbstractUserController {
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    static final String REST_URL = "/api/profile";

    @GetMapping
    public User get(User user) {
        return get(authUserId());
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(int id) {
        super.delete(authUserId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(allEntries = true)
    public User register(@RequestBody User user) {
        log.info("Register {}", user);
        checkNew(user);
        return save(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(allEntries = true)
    public void update(@RequestBody User user, @RequestBody int id) {
        log.info("Update {} with id {}", user, id);
        assureIdConsistent(user, id);
        save(user);
    }
}
