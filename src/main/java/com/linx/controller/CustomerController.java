package com.linx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linx.model.Customer;
import com.linx.repository.CustomerRepository;


@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {


	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping
	public ResponseEntity<List<Customer>> getAll() {
		return ResponseEntity.ok(customerRepository.findAll());

	}


}
