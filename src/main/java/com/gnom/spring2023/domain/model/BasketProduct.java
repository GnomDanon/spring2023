package com.gnom.spring2023.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Соотношение корзины и товаров, которые лежат в ней.
 * Служит для устранения связи "многие-ко-многим" между таблицами "Корзины" и "Товары".
 */
@Getter
@Setter
public class BasketProduct {
    /**
     * ID соотношения
     */
    private Long id;

    /**
     * ID корзины
     */
    private Long basketId;

    /**
     * ID товара
     */
    private Long productId;

    /**
     * Количество товара, которое заказывает пользователь
     */
    private int count;

    public BasketProduct() {}
}
