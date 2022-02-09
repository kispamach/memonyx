package com.codecool.memonyx.exception;

public class ShoppingNotFoundException extends RuntimeException{

    public ShoppingNotFoundException(Long id) {
        super("Could not find shopping " + id);
    }
}
