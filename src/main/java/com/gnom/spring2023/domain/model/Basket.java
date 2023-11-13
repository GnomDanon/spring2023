package com.gnom.spring2023.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Корзина, в которой содержится информация о товарах, рассматриваемых пользователем в данный момент
 */
@Getter
@Setter
public class Basket {
    /**
     * ID корзины
     */
    private Long id;

    /**
     * Суммарная стоимость товаров, лежащих в корзине
     */
    private double finalCost;

    /**
     * ID пользователя, которому принадлежит корзина
     */
    private Long userId;

    public Basket() {}
}
