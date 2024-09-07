package ru.serpov.restaurantvoting.error;

import org.springframework.http.HttpStatus;

public class IllegalRequestDataException extends CustomException {

    public IllegalRequestDataException(String msg) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, msg);
    }
}
