package com.gnom.spring2023.app.exception.product;

public class ProductAlreadyExistException extends Exception{
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
