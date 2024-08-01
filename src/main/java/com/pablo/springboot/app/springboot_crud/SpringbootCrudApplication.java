package com.pablo.springboot.app.springboot_crud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pablo.springboot.app.springboot_crud.entities.Client;
import com.pablo.springboot.app.springboot_crud.entities.Order;
import com.pablo.springboot.app.springboot_crud.repositories.ClientRepository;
import com.pablo.springboot.app.springboot_crud.repositories.OrderRepository;

@SpringBootApplication
public class SpringbootCrudApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

	public void oneToMany(){

		Client client = new Client("Jorge", "Macri");
		clientRepository.save(client);

		Order order = new Order("es una orden muy completa", 400L);
		order.setClient(client);
		Order orderDB = orderRepository.save(order);
		System.out.println(orderDB);
	}

}
