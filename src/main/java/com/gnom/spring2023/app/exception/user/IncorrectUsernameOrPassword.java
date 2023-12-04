package com.gnom.spring2023.app.exception.user;

public class IncorrectUsernameOrPassword extends Exception{
    public IncorrectUsernameOrPassword() {
        super("Неверное имя пользователя или пароль");
    }
}
