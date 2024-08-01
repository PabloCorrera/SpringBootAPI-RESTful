package com.pablo.springboot.app.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pablo.springboot.app.springboot_crud.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{
    
}
