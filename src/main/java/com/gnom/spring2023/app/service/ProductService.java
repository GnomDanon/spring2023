package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.entity.ProductEntity;
import com.gnom.spring2023.app.exception.product.ProductAlreadyExistException;
import com.gnom.spring2023.app.exception.product.ProductNotFoundException;
import com.gnom.spring2023.app.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public ProductEntity create(ProductEntity product) throws ProductAlreadyExistException {
        if (productRepo.findByName(product.getName()) != null) {
            throw new ProductAlreadyExistException("Товар с таким названием уже существует");
        }
        return productRepo.save(product);
    }

    public Iterable<ProductEntity> getAll() {
        return productRepo.findAll();
    }

    public ProductEntity getById(Long id) throws ProductNotFoundException {
        ProductEntity product = productRepo.findById(id).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException("Товар не найден");
        }
        return product;
    }

    public ProductEntity getByName(String name) throws ProductNotFoundException {
        ProductEntity product = productRepo.findByName(name);
        if (product == null) {
            throw new ProductNotFoundException("Товар не найден");
        }
        return product;
    }

    public Iterable<ProductEntity> getByAvailable(boolean available) {
        return productRepo.findByAvailable(available);
    }

    public Iterable<ProductEntity> getByType(String type) {
        return productRepo.findByProductType(type);
    }

    public void deleteByName(String name) throws ProductNotFoundException {
        ProductEntity product = productRepo.findByName(name);
        if (product == null) {
            throw new ProductNotFoundException("Товар не найден");
        }
        productRepo.deleteByName(name);
    }

    public void update(String name, boolean available, int costPerUnit, String composition)
            throws ProductNotFoundException{
        ProductEntity product = productRepo.findByName(name);
        if (product == null) {
            throw new ProductNotFoundException("Товар не найден");
        }
        product.setName(name);
        product.setAvailable(available);
        product.setCostPerUnit(costPerUnit);
        product.setComposition(composition);
        productRepo.save(product);
    }
}
