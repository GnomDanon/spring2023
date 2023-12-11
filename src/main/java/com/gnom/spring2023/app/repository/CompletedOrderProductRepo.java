package com.gnom.spring2023.app.repository;

import com.gnom.spring2023.app.entity.CompletedOrderProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompletedOrderProductRepo extends CrudRepository<CompletedOrderProductEntity, Long> {

}
