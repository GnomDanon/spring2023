package com.gnom.spring2023.app.exception.basket;

public class BasketNotFoundException extends Exception{
    public BasketNotFoundException() {
        super("Корзина не найдена");
    }
}
