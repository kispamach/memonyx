package com.codecool.memonyx.exception;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(Throwable cause, Long id) {
        super("Could not find cart " + id, cause);
    }

    public CartNotFoundException(Long id) {
        super("Could not find cart " + id);
    }
}
