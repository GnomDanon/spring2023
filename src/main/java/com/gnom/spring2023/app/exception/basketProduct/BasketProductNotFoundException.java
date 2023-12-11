package com.gnom.spring2023.app.exception.basketProduct;

public class BasketProductNotFoundException extends Exception{
    public BasketProductNotFoundException() {
        super("Данное соотношение корзины и товара не найдено");
    }
}
