package com.gnom.spring2023.app.entity;

import com.gnom.spring2023.domain.model.Product;
import com.gnom.spring2023.domain.model.ProductType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productType;
    private String name;
    private boolean available;
    private int costPerUnit;
    private String composition;

    @OneToMany(mappedBy = "productEntity", orphanRemoval = true)
    private ArrayList<BasketProductEntity> basketProductEntities;

    @OneToMany(mappedBy = "productEntity", orphanRemoval = true)
    private ArrayList<CompletedOrderProductEntity> completedOrderProductEntities;

    public static Product toModel(ProductEntity productEntity) {
        Product model = new Product();
        model.setId(productEntity.getId());
        model.setProductType(productEntity.getProductType());
        model.setName(productEntity.getName());
        model.setAvailable(productEntity.isAvailable());
        model.setCostPerUnit(productEntity.getCostPerUnit());
        model.setComposition(productEntity.getComposition());
        return model;
    }
}
