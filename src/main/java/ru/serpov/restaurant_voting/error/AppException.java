package ru.serpov.restaurant_voting.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AppException extends ResponseStatusException {
    private final String options;
//    private final ErrorAttributeOptions options;

    public AppException(HttpStatus status, String message, String options) {
        super(status, message);
        this.options = options;
    }

    @Override
    public String getMessage() {
        return getReason();
    }
}
