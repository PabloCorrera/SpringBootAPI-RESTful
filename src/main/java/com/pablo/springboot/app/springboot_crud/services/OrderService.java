package com.pablo.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import com.pablo.springboot.app.springboot_crud.entities.Order;

public interface OrderService {
    
    List<Order> findAll();

    Optional<Order> findOrderById(Long id);

    Order saveOrder(Order order, Long id);

    Optional<Order> updateOrder(Long id, Order order);

    Optional<Order> delete(Long id);

    List<Order> deleteByClientId(Long id);
}
