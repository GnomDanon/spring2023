package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.entity.BasketEntity;
import com.gnom.spring2023.app.exception.basket.BasketAlreadyExistException;
import com.gnom.spring2023.app.exception.user.IncorrectUsernameOrPassword;
import com.gnom.spring2023.app.exception.user.UserAlreadyExistException;
import com.gnom.spring2023.app.exception.user.UserNotFoundException;
import com.gnom.spring2023.app.repository.UserRepo;
import com.gnom.spring2023.app.entity.UserEntity;
import com.gnom.spring2023.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private BasketService basketService;

    @Autowired
    private UserRepo userRepo;

    /**
     * Регистрация пользователя
     * @param user Пользователь, которого нужно зарегистрировать
     * @return Зарегистрированный пользователь
     * @throws UserAlreadyExistException Пользователь с указанным именем пользователя уже существует
     */
    public User registration(UserEntity user) throws UserAlreadyExistException,
            IncorrectUsernameOrPassword, BasketAlreadyExistException {
        if (Objects.equals(user.getUsername(), "") || Objects.equals(user.getPassword(), "")) {
            throw new IncorrectUsernameOrPassword();
        }

        var candidate = userRepo.findByUsername(user.getUsername());

        if (candidate != null) {
            throw new UserAlreadyExistException();
        }

        var hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);

        BasketEntity basket = new BasketEntity();
        basket.setUserEntity(user);
        basket.setFinalCost(0);
        basketService.create(basket);
        return UserEntity.toModel(userRepo.save(user));
    }

    /**
     * Аутентификация пользователя (Позднее добавлю JWT-токены)
     * @param username Имя пользователя
     * @param password Пароль
     * @return Авторизованный пользователь
     * @throws IncorrectUsernameOrPassword Неверное имя пользователя или пароль
     */
    public User login(String username, String password) throws IncorrectUsernameOrPassword {
        if (Objects.equals(username, "") || Objects.equals(password, "")) {
            throw new IncorrectUsernameOrPassword();
        }

        var candidate = userRepo.findByUsername(username);
        if (candidate == null) {
            throw new IncorrectUsernameOrPassword();
        }

        var isPassEquals = passwordEncoder.matches(password, candidate.getPassword());
        if (!isPassEquals) {
            throw new IncorrectUsernameOrPassword();
        }
        return UserEntity.toModel(candidate);
    }

    /**
     * Выход из учетной записи
     */
    public void logout() {

    }

    /**
     * Получение пользователя по его ID
     * @param id ID Пользователя, которого нужно получить
     * @return Найденный пользователь
     * @throws UserNotFoundException Пользователь с таким ID не существует
     */
    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return UserEntity.toModel(user);
    }
}
