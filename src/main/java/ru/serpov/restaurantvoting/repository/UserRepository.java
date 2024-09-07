package ru.serpov.restaurantvoting.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.serpov.restaurantvoting.error.NotFoundException;
import ru.serpov.restaurantvoting.model.entity.impl.UserEntity;

import java.util.Optional;

import static ru.serpov.restaurantvoting.config.SecurityConfig.PASSWORD_ENCODER;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<UserEntity> {

    @Query("SELECT u FROM UserEntity u WHERE u.email = LOWER(:email)")
    Optional<UserEntity> findByEmailIgnoreCase(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.enabled = :enabled WHERE u.id = :id")
    void updateEnabled(int id, boolean enabled);

    @Transactional
    default UserEntity prepareAndSave(UserEntity user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return save(user);
    }

    default UserEntity getExistedByEmail(String email) {
        return findByEmailIgnoreCase(email).orElseThrow(() -> new NotFoundException("User with email=" + email + " not found"));
    }
}
