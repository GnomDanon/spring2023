package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.repository.CompletedOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompletedOrderService {
    @Autowired
    private CompletedOrderRepo completedOrderRepo;
}
