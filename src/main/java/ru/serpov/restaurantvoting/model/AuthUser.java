package ru.serpov.restaurantvoting.model;

import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.User;
import ru.serpov.restaurantvoting.model.entity.impl.UserEntity;

@Getter
public class AuthUser extends User {

    private final UserEntity user;

    public AuthUser(@NonNull UserEntity user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }

    @Override
    public String toString() {
        return "AuthUserEntity:" + id() + '[' + user.getEmail() + ']';
    }
}