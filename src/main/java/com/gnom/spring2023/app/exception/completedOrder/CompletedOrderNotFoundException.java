package com.gnom.spring2023.app.exception.completedOrder;

public class CompletedOrderNotFoundException extends Exception{
    public CompletedOrderNotFoundException() {
        super("Заказ не найден");
    }
}
