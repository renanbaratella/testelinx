package com.linx.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.linx.model.Customer;
import com.linx.model.Order;
import com.linx.repository.CustomerRepository;
import com.linx.repository.OrderRepository;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping
	public ResponseEntity<List<Order>> getAll() {
		return ResponseEntity.ok(orderRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Order> postOrder(@Valid @RequestBody Order order) {
		
		Optional<Customer> buscausuario = customerRepository.findByEmail(order.getCustomer().getEmail());
		if (buscausuario.isPresent() && buscausuario.get().getId() != order.getCustomer().getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email de customer já existe, null");
		}
		
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(orderRepository.save(order));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> putOrder(@PathVariable Long id, @RequestBody Order order) {
		order.setId(id);
		
		Optional<Order> buscaId = orderRepository.findById(id);
		
		if(!buscaId.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		order.setShipping(buscaId.get().getShipping());
		order.setItem(buscaId.get().getItem());
		
		return ResponseEntity.status(HttpStatus.OK).body(orderRepository.save(order));
	}

}