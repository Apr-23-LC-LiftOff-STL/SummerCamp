package com.campquest.exception;

public class CampNotFoundException extends RuntimeException {
    public CampNotFoundException(String message) {
        super(message);
    }
}
