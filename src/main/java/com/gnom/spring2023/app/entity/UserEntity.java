package com.gnom.spring2023.app.entity;

import com.gnom.spring2023.domain.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToOne(mappedBy = "userEntity", orphanRemoval = true)
    private BasketEntity basketEntity;

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)
    private ArrayList<CompletedOrderEntity> completedOrderEntities;

    public UserEntity() {
    }

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        return model;
    }
}
