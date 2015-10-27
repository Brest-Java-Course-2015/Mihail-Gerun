package com.epam.brest.course2015.app;

/**
 * Created by bendar on 26/10/15.
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}