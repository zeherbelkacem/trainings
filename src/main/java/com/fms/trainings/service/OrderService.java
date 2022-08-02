package com.fms.trainings.service;

import com.fms.trainings.entities.Order;

import java.util.Map;

public interface OrderService {
    public Order getById(Long customerId);
    public Long saveOrder(Order order);

    public long getLastOrderId();
}
