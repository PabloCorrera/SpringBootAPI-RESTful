package com.pablo.springboot.app.springboot_crud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.springboot.app.springboot_crud.entities.Order;
import com.pablo.springboot.app.springboot_crud.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> list(){
        return orderService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Order> optionalOrder = orderService.findOrderById(id);
        if(optionalOrder.isPresent()){
            return ResponseEntity.ok(optionalOrder.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> findOrderById(@PathVariable Long id){
        Optional<Order> optionalOrder = orderService.findOrderById(id);
        if(optionalOrder.isPresent()){
            return ResponseEntity.ok(optionalOrder.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<Order> create(@PathVariable Long clientId, @RequestBody Order order){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(order, clientId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order) {
        Optional<Order> optionalOrder = orderService.updateOrder(id, order);
        if(optionalOrder.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalOrder.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable Long id){
        Optional<Order> optionalOrder = orderService.delete(id);
        if(optionalOrder.isPresent()){
            return ResponseEntity.ok(optionalOrder.orElseThrow());
        }
        return ResponseEntity.notFound().build();
        
    }
}
