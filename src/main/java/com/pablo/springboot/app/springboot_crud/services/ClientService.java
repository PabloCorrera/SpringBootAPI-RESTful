package com.pablo.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;


import com.pablo.springboot.app.springboot_crud.entities.Client;

public interface ClientService {
    
    List<Client> findAll();

    Optional<Client> findClientById(Long id);

    Client saveClient(Client client);

    Optional<Client> updateClient(Long id, Client client);

    Optional<Client> delete(Long id);
}
