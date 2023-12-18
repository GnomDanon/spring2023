package com.gnom.spring2023.extern.controller;

import com.gnom.spring2023.app.entity.BasketProductEntity;
import com.gnom.spring2023.app.exception.basket.BasketNotFoundException;
import com.gnom.spring2023.app.exception.basketProduct.BasketProductAlreadyExistException;
import com.gnom.spring2023.app.exception.basketProduct.BasketProductNotFoundException;
import com.gnom.spring2023.app.exception.product.ProductNotFoundException;
import com.gnom.spring2023.app.service.BasketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basketProducts")
public class BasketProductController {
    @Autowired
    BasketProductService basketProductService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody BasketProductEntity basketProduct) {
        try {
            basketProductService.create(basketProduct);
            return ResponseEntity.ok("Соотношение успешно создано");
        } catch (BasketNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (BasketProductAlreadyExistException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла неизвестная ошибка");
        }
    }

    @GetMapping("/getAllByBasketId")
    public ResponseEntity getAllByBasketId(@RequestParam Long basketId) {
        try {
            return ResponseEntity.ok(basketProductService.getAllByBasketId(basketId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла неизвестная ошибка");
        }
    }

    @DeleteMapping("/deleteAllByBasketId")
    public ResponseEntity deleteAllByBasketId(@RequestParam Long basketId) {
        try {
            basketProductService.deleteAllByBasketId(basketId);
            return ResponseEntity.ok("Соотношения успешно удалены");
        } catch (BasketNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла неизвестная ошибка");
        }
    }

    @DeleteMapping("/deleteOneByBasketIdAndProductId")
    public ResponseEntity deleteOneByBasketIdAndProductId(@RequestParam Long basketId, @RequestParam Long productId) {
        try {
            basketProductService.deleteOneByBasketIdAndProductId(basketId, productId);
            return ResponseEntity.ok("Соотношение успешно удалено");
        } catch (BasketNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла неизвестная ошибка");
        }
    }

    @PutMapping("/changeCountById")
    public ResponseEntity changeCountById(@RequestParam Long id, @RequestParam int count) {
        try {
            basketProductService.changeCountById(id, count);
            return ResponseEntity.ok("Количество успешно изменено");
        } catch (BasketNotFoundException | BasketProductNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла неизвестная ошибка");
        }
    }
}
