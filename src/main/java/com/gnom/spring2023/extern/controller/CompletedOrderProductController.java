package com.gnom.spring2023.extern.controller;

import com.gnom.spring2023.app.entity.CompletedOrderProductEntity;
import com.gnom.spring2023.app.exception.completedOrderProduct.CompletedOrderProductAlreadyExistException;
import com.gnom.spring2023.app.service.CompletedOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/completedOrderProducts")
public class CompletedOrderProductController {

    @Autowired
    CompletedOrderProductService completedOrderProductService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody CompletedOrderProductEntity completedOrderProduct) {
        try {
            completedOrderProductService.create(completedOrderProduct);
            return ResponseEntity.ok("Соотношение успешно создано");
        } catch (CompletedOrderProductAlreadyExistException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getAllByCompletedOrderId")
    public ResponseEntity getAllByCompletedOrderId(@RequestParam Long completedOrderId) {
        try {
            return ResponseEntity.ok(completedOrderProductService.getAllByCompletedOrderId(completedOrderId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
