package ru.serpov.restaurant_voting.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import ru.serpov.restaurant_voting.error.NotFoundException;
import ru.serpov.restaurant_voting.model.User;
import ru.serpov.restaurant_voting.repository.UserRepository;

public abstract class AbstractUserController {
    private static final Logger log = LoggerFactory.getLogger(AbstractUserController.class);

    @Autowired
    protected UserRepository repository;

    public User get(int id) {
        log.info("get {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User with id " + id + " doesn't exist"));
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    protected User save(User user) {
        return repository.save(user);
    }
}
