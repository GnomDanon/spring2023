package com.gnom.spring2023.app.entity;

import com.gnom.spring2023.domain.model.BasketProduct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BasketProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count;

    @ManyToOne
    @JoinColumn
    private BasketEntity basketEntity;

    @ManyToOne
    @JoinColumn
    private ProductEntity productEntity;

    public BasketProductEntity() {}

    public static BasketProduct toModel(BasketProductEntity basketProductEntity) {
        BasketProduct model = new BasketProduct();
        model.setId(basketProductEntity.getId());
        model.setBasketId(basketProductEntity.getBasketEntity().getId());
        model.setProductId(basketProductEntity.getProductEntity().getId());
        model.setCount(basketProductEntity.getCount());
        return model;
    }
}
