package com.gnom.spring2023.app.repository;

import com.gnom.spring2023.app.entity.BasketEntity;
import org.springframework.data.repository.CrudRepository;

public interface BasketRepo extends CrudRepository<BasketEntity, Long> {
    BasketEntity findByUserEntity_Id(Long userEntity_Id);
}
