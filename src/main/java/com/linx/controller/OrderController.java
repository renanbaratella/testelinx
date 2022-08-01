package com.linx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linx.model.Order;
import com.linx.repository.OrderRepository;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping
	public ResponseEntity<List<Order>> getAll() {
		return ResponseEntity.ok(orderRepository.findAll());

	}

}