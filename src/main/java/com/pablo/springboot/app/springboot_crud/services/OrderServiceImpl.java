package com.pablo.springboot.app.springboot_crud.services;

import java.lang.classfile.ClassFile.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablo.springboot.app.springboot_crud.entities.Client;
import com.pablo.springboot.app.springboot_crud.entities.Order;
import com.pablo.springboot.app.springboot_crud.repositories.ClientRepository;
import com.pablo.springboot.app.springboot_crud.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
 
    @Autowired
    private OrderRepository repository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Order> findAll() {
        return (List<Order>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Order> findOrderById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Order saveOrder(Order order, Long id) {
        Optional<Client> c = clientRepository.findById(id);
        if(c.isPresent()){
            order.setClient(c.get());
            return repository.save(order);    
        }
        return null;

    }

    @Transactional
    @Override
    public Optional<Order> updateOrder(Long id, Order order){
        Optional<Order> optionalOrder = repository.findById(id);
        if(optionalOrder.isPresent()){
            Order orderDb = optionalOrder.orElseThrow();

            orderDb.setDescription(order.getDescription());
            orderDb.setTotal(order.getTotal());
            orderDb.setClient(order.getClient());
            return Optional.of(repository.save(orderDb));
        }
        return optionalOrder;

    }

    @Transactional
    @Override
    public Optional<Order> delete(Long id) {
        Optional<Order> optionalOrder = repository.findById(id);
        optionalOrder.ifPresent(c -> {
            repository.delete(c);
        });
        return optionalOrder;
        
    }

    @Transactional
    @Override
    public List<Order> deleteByClientId(Long id) {
        List<Order> ordersWithId = new ArrayList<>();
        ordersWithId = repository.findAllByClientId(id);
        if(!ordersWithId.isEmpty()){
            repository.deleteAll(ordersWithId);
        }
        
        return ordersWithId;
        
    }
    
}
