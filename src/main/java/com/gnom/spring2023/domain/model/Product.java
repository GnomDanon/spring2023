package com.gnom.spring2023.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String productType;
    private String name;
    private boolean available;
    private int costPerUnit;
    private String composition;

    public Product() {

    }
}
