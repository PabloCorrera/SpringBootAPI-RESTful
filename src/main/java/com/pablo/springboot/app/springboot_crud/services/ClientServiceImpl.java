package com.pablo.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablo.springboot.app.springboot_crud.entities.Client;
import com.pablo.springboot.app.springboot_crud.entities.Order;
import com.pablo.springboot.app.springboot_crud.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository repository;

    @Autowired
    private OrderService orderService;

    @Transactional(readOnly = true)
    @Override
    public List<Client> findAll() {
        return (List<Client>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Client> findClientById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Client saveClient(Client client) {
        return repository.save(client);
    }

    @Transactional
    @Override
    public Optional<Client> updateClient(Long id, Client client){
        Optional<Client> optionalClient = repository.findById(id);
        if(optionalClient.isPresent()){
            Client clientDb = optionalClient.orElseThrow();

            clientDb.setName(client.getName());
            clientDb.setLastName(client.getLastName());
            return Optional.of(repository.save(clientDb));
        }
        return optionalClient;

    }

    @Transactional
    @Override
    public Optional<Client> delete(Long id) {
        Optional<Client> optionalClient = repository.findById(id);
        optionalClient.ifPresent(c -> {
            
            List<Order> deletedOrders = orderService.deleteByClientId(id);
            System.out.println(deletedOrders);
            repository.delete(c);
        });
        return optionalClient;
        
    }
    
}
