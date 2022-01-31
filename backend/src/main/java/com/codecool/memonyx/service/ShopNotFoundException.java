package com.codecool.memonyx.service;

public class ShopNotFoundException extends RuntimeException{

    public ShopNotFoundException(Long id) {
        super("Could not find shop " + id);
    }
}
