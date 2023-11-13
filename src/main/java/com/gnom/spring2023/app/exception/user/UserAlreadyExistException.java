package com.gnom.spring2023.app.exception.user;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException() {
        super("Пользователь с таким именем уже существует");
    }
}
