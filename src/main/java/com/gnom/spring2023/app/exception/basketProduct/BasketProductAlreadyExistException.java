package com.gnom.spring2023.app.exception.basketProduct;

public class BasketProductAlreadyExistException extends Exception{
    public BasketProductAlreadyExistException() {
        super("Данное соотношение корзины и товара уже установлено");
    }
}
