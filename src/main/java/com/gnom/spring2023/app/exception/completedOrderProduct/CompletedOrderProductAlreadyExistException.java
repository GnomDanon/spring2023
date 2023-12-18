package com.gnom.spring2023.app.exception.completedOrderProduct;

public class CompletedOrderProductAlreadyExistException extends Exception{
    public CompletedOrderProductAlreadyExistException() {
        super("Данное соотношение завершенного заказа и товара уже существует");
    }
}
