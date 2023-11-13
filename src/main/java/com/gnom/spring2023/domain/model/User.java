package com.gnom.spring2023.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Пользователь, посетитель сайта
 */
@Getter
@Setter
public class User {
    /**
     * ID пользователя
     */
    private Long id;
    /**
     * Имя пользователя
     */
    private String username;

    public User() {
    }
}