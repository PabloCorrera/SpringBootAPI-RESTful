package com.pablo.springboot.app.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pablo.springboot.app.springboot_crud.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
    
}
