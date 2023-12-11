package com.gnom.spring2023.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
public class CompletedOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private double completedSum;

    private String orderTime;

    private String completedTime;

    @OneToMany(mappedBy = "completedOrderEntity", orphanRemoval = true)
    private ArrayList<BasketProductEntity> basketProductEntities;

    @ManyToOne
    private UserEntity userEntity;

    public CompletedOrderEntity() {}
}
