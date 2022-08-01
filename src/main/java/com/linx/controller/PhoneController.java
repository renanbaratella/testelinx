package com.linx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linx.model.Phone;
import com.linx.repository.PhoneRepository;

@RestController
@RequestMapping("/phone")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PhoneController {
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@GetMapping
	public ResponseEntity<List<Phone>> getAll() {
		return ResponseEntity.ok(phoneRepository.findAll());

	}

}
