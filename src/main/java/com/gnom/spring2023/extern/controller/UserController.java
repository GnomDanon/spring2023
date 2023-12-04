package com.gnom.spring2023.extern.controller;

import com.gnom.spring2023.app.exception.user.IncorrectUsernameOrPassword;
import com.gnom.spring2023.app.exception.user.UserAlreadyExistException;
import com.gnom.spring2023.app.exception.user.UserNotFoundException;
import com.gnom.spring2023.app.service.UserService;
import com.gnom.spring2023.app.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("Пользователь был успешно сохранен");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) {
        try {
            return ResponseEntity.ok(userService.login(username, password));
        } catch (IncorrectUsernameOrPassword e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла ошибка");
        }
    }

    @GetMapping("/getOne")
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
