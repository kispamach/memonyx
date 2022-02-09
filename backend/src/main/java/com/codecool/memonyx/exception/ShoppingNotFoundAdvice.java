package com.codecool.memonyx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ShoppingNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ShoppingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String shoppingNotFoundHandler(ShoppingNotFoundException ex) {
        return ex.getMessage();
    }
}
