package ru.serpov.restaurant_voting.error;

import org.springframework.http.HttpStatus;

public class IllegalRequestDataException extends AppException {
    public IllegalRequestDataException(String msg) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, msg, "options");
//                ErrorAttributeOptions.of(MESSAGE));
    }
}
