package com.gnom.spring2023.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Товар, единица ассортимента
 */
@Getter
@Setter
public class Product {
    /**
     * ID товара
     */
    private Long id;
    /**
     * Категория (мясо, салат, овощ, хлебобулочное изделие, молочная продукция
     */
    private String productType;
    /**
     * Название
     */
    private String name;
    /**
     * Доступность в данный момент времени
     */
    private boolean available;
    /**
     * Стоимость за единицу товара (за 1 штуку / за 1 кг / за 100 г)
     */
    private int costPerUnit;
    /**
     * Состав
     */
    private String composition;

    public Product() {

    }
}
