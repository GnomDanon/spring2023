package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.exception.UserAlreadyExistException;
import com.gnom.spring2023.app.exception.UserNotFoundException;
import com.gnom.spring2023.app.repository.UserRepo;
import com.gnom.spring2023.app.entity.UserEntity;
import com.gnom.spring2023.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return UserEntity.toModel(user);
    }
}
