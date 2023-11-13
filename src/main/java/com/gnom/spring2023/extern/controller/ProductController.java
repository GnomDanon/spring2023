package com.gnom.spring2023.extern.controller;

import com.gnom.spring2023.app.entity.ProductEntity;
import com.gnom.spring2023.app.exception.product.ProductAlreadyExistException;
import com.gnom.spring2023.app.exception.product.ProductNotFoundException;
import com.gnom.spring2023.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ProductEntity product) {
        try {
            productService.create(product);
            return ResponseEntity.ok("Товар успешно создан");
        } catch (ProductAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(productService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getById")
    public ResponseEntity getById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(productService.getById(id));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getByName")
    public ResponseEntity getByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(productService.getByName(name));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getByAvailable")
    public ResponseEntity getByAvailable(@RequestParam boolean available) {
        try {
            return ResponseEntity.ok(productService.getByAvailable(available));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getByType")
    public ResponseEntity getByType(@RequestParam String type) {
        try {
            return ResponseEntity.ok(productService.getByType(type));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity deleteByName(@PathVariable String name) {
        try {
            productService.deleteByName(name);
            return ResponseEntity.ok("Товар успешно удален");
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestParam Long id,
                                 @RequestParam String name,
                                 @RequestParam boolean available,
                                 @RequestParam int costPerUnit,
                                 @RequestParam String composition) {
        try {
            productService.update(id, name, available, costPerUnit, composition);
            return ResponseEntity.ok("Товар успешно обновлен");
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
