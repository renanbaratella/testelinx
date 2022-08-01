package com.linx.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linx.model.Customer;
import com.linx.model.CustomerLogin;
import com.linx.repository.CustomerRepository;
import com.linx.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Customer>> getAll() {
		List<Customer> customerList = customerRepository.findAll();

		if (customerList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (Customer customer : customerList) {
				Long id = customer.getId();
				customer.add(linkTo(methodOn(CustomerController.class).getCustomerById(id)).withSelfRel());
			}
			return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		
		Optional<Customer> customer = customerRepository.findById(id);
		
		if(!customer.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			customer.get().add(linkTo(methodOn(CustomerController.class).getAll()).withRel("List of Customer"));
			return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
		}
	
	}

	@PostMapping("/logar")
	public ResponseEntity<CustomerLogin> login(@RequestBody Optional<CustomerLogin> customerLogin) {
		return customerService.autenticarCustomer(customerLogin)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Customer> postCustomer(@Valid @RequestBody Customer customer) {
		return customerService.cadastrarCustomer(customer)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> putCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		customer.setId(id);
		return customerService.atualizarCustomer(customer)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PatchMapping(path = "/{id}")
	public ResponseEntity<Customer> patchCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		customer.setId(id);
		if(!customerService.patchCustomer(customer).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {	
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}
	}

}
