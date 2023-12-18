package com.gnom.spring2023.extern.controller;

import com.gnom.spring2023.app.exception.basket.BasketNotFoundException;
import com.gnom.spring2023.app.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baskets")
public class BasketController {
    @Autowired
    BasketService basketService;

    @GetMapping("/getByUserId")
    public ResponseEntity getByUserId(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(basketService.getByUserId(userId));
        } catch (BasketNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла неизвестная ошибка");
        }
    }

    @GetMapping("/getById")
    public ResponseEntity getById(@RequestParam Long Id) {
        try {
            return ResponseEntity.ok(basketService.getById(Id));
        } catch (BasketNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла неизвестная ошибка");
        }
    }
}
