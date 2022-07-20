package ru.serpov.restaurant_voting.repository;

import ru.serpov.restaurant_voting.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> getByEmail(String email);
}
