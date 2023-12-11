package com.gnom.spring2023.app.repository;

import com.gnom.spring2023.app.entity.BasketProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface BasketProductRepo extends CrudRepository<BasketProductEntity, Long> {
    Iterable<BasketProductEntity> findAllByBasketEntity_Id(Long basketEntity_Id);

    BasketProductEntity findByBasketEntity_IdAndProductEntity_Id(Long basketEntity_Id, Long productEntity_Id);

    void deleteAllByBasketEntity_Id(Long basketEntity_Id);

    void deleteByBasketEntity_IdAndProductEntity_Id(Long basketEntityId, Long productEntityId);
}
