package com.gnom.spring2023.app.entity;

import com.gnom.spring2023.domain.model.Basket;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn
    private UserEntity userEntity;

    private double finalCost;

    @OneToMany(mappedBy = "basketEntity")
    private ArrayList<BasketProductEntity> basketProductEntities;

    public BasketEntity() {}

    public static Basket toModel(BasketEntity basketEntity) {
        Basket model = new Basket();
        model.setId(basketEntity.getId());
        model.setFinalCost(basketEntity.getFinalCost());
        model.setUserId(basketEntity.getUserEntity().getId());
        return model;
    }
}
