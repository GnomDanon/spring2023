package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.repository.CompletedOrderProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompletedOrderProductService {
    @Autowired
    private CompletedOrderProductRepo completedOrderProductRepo;
}
