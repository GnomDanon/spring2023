package com.gnom.spring2023.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompletedOrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private CompletedOrderEntity completedOrderEntity;

    @ManyToOne
    @JoinColumn
    private ProductEntity productEntity;

    public int count;

    public CompletedOrderProductEntity() {}
}
