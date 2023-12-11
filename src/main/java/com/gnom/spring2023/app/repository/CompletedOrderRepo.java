package com.gnom.spring2023.app.repository;

import com.gnom.spring2023.app.entity.CompletedOrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompletedOrderRepo extends CrudRepository<CompletedOrderEntity, Long> {
}
