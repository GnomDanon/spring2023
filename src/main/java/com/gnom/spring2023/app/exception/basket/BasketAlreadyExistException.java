package com.gnom.spring2023.app.exception.basket;

public class BasketAlreadyExistException extends Exception{
    public BasketAlreadyExistException() {
        super("Корзина для данного пользователя уже создана");
    }
}
