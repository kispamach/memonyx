package com.codecool.memonyx.service;

public class ShoppingNotFoundException extends RuntimeException{

    public ShoppingNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
