package com.codecool.memonyx.exception;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(Long id) {
        super("Could not find cart " + id);
    }
}
