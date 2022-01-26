package com.codecool.memonyx.service;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
