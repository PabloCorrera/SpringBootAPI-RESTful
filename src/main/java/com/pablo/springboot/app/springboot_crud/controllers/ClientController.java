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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.springboot.app.springboot_crud.entities.Client;
import com.pablo.springboot.app.springboot_crud.services.ClientService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/clients")
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> list(){
        return clientService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Client> optionalClient = clientService.findClientById(id);
        if(optionalClient.isPresent()){
            return ResponseEntity.ok(optionalClient.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> findClientById(@PathVariable Long id){
        Optional<Client> optionalClient = clientService.findClientById(id);
        if(optionalClient.isPresent()){
            return ResponseEntity.ok(optionalClient.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        Optional<Client> optionalClient = clientService.updateClient(id, client);
        if(optionalClient.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalClient.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id){
        Optional<Client> optionalClient = clientService.delete(id);
        if(optionalClient.isPresent()){
            return ResponseEntity.ok(optionalClient.orElseThrow());
        }
        return ResponseEntity.notFound().build();   
    }

   
}
