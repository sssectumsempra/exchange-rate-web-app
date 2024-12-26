package com.exchangerate.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Naming should be very accurate
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(String message) {
        super(message);
    }

    //you should leave redundant code
    public CurrencyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
