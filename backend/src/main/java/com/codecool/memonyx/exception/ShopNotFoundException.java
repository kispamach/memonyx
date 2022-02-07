package com.codecool.memonyx.exception;

public class ShopNotFoundException extends RuntimeException{

    public ShopNotFoundException(Long id) {
        super("Could not find shop " + id);
    }
}
