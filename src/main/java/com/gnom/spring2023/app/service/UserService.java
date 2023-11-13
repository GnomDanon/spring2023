package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.exception.user.UserAlreadyExistException;
import com.gnom.spring2023.app.exception.user.UserNotFoundException;
import com.gnom.spring2023.app.repository.UserRepo;
import com.gnom.spring2023.app.entity.UserEntity;
import com.gnom.spring2023.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    /**
     * Регистрация пользователя
     * @param user Пользователь, которого нужно зарегистрировать
     * @return Зарегистрированный пользователь
     * @throws UserAlreadyExistException Пользователь с указанным именем пользователя уже существует
     */
    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException();
        }
        return userRepo.save(user);
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
