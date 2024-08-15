package com.InstaTalk.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserException extends Exception {

    public UserException(String message) {
        super(message);
    }
}
