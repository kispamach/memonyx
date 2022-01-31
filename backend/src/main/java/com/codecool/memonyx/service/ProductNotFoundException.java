package com.codecool.memonyx.service;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long id) {
        super("Could not find product " + id);
    }
}
