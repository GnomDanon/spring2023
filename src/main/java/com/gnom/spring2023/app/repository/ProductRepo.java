package com.gnom.spring2023.app.repository;

import com.gnom.spring2023.app.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<ProductEntity, Long> {
    ProductEntity findByName(String name);
    Iterable<ProductEntity> findByAvailable(boolean available);
    Iterable<ProductEntity> findByProductType(String productType);
    void deleteByName(String name);
}
