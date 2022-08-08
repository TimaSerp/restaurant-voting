package ru.serpov.restaurant_voting.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.serpov.restaurant_voting.error.IllegalRequestDataException;
import ru.serpov.restaurant_voting.model.User;
import ru.serpov.restaurant_voting.util.ValidationUtil;

import java.util.List;
import java.util.Optional;

import static ru.serpov.restaurant_voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController extends AbstractUserController {
    private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);

    static final String REST_URL = "/api/admin/users";

    @Override
    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @GetMapping
    @Cacheable(cacheNames = "users")
    public List<User> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name", "email"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true, cacheNames = "users")
    public User create(@RequestBody User user) {
        log.info("create {}", user);
        checkNew(user);
        return save(user);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true, cacheNames = "users")
    public void update(@RequestBody User user, @PathVariable int id) {
        log.info("Update {} with id {}", user, id);
        ValidationUtil.assureIdConsistent(user, id);
        save(user);
    }

    @GetMapping("/by-email")
    public Optional<User> getByEmail(@RequestParam String email) {
        log.info("Get by Email {}", email);
        return Optional.ofNullable(repository.getByEmail(email).orElseThrow(() -> new IllegalRequestDataException
                ("User with email " + email + " doesn't exist")));
    }
}
