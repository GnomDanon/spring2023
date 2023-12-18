package com.gnom.spring2023.extern.controller;

import com.gnom.spring2023.app.entity.CompletedOrderEntity;
import com.gnom.spring2023.app.exception.basket.BasketNotFoundException;
import com.gnom.spring2023.app.exception.basketProduct.BasketProductAlreadyExistException;
import com.gnom.spring2023.app.exception.completedOrder.CompletedOrderNotFoundException;
import com.gnom.spring2023.app.exception.product.ProductNotFoundException;
import com.gnom.spring2023.app.service.CompletedOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/completedOrders")
public class CompletedOrderController {
    @Autowired
    CompletedOrderService completedOrderService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody CompletedOrderEntity completedOrder) {
        try {
            completedOrderService.create(completedOrder);
            return ResponseEntity.ok("Завершенный заказ успешно создан");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла неизвестная ошибка");
        }
    }

    @GetMapping("/getAllByUserId")
    public ResponseEntity getAllByUserId(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(completedOrderService.getAllByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла неизвестная ошибка");
        }
    }

    @GetMapping("/getById")
    public ResponseEntity getById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(completedOrderService.getById(id));
        } catch (CompletedOrderNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла неизвестная ошибка");
        }
    }

    @PutMapping("/repeatOrderByCompletedOrderIdAndBasketId")
    public ResponseEntity repeatOrderByCompletedOrderIdAndBasketId(@RequestParam Long completedOrderId,
                                                                   @RequestParam Long basketId) {
        try {
            completedOrderService.repeatOrderByCompletedOrderIdAndBasketId(completedOrderId, basketId);
            return ResponseEntity.ok("Заказ успешно повторен");
        } catch (BasketNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (BasketProductAlreadyExistException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Произошла неизвестная ошибка");
        }
    }
}
