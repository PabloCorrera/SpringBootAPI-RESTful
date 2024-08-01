package com.pablo.springboot.app.springboot_crud.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pablo.springboot.app.springboot_crud.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
    
     @Query("SELECT o FROM Order o WHERE o.client.id = ?1")
    List<Order> findAllByClientId(Long clientId);

}
