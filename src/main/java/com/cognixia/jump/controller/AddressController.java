package com.cognixia.jump.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Address;
import com.cognixia.jump.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService service;

	// CREATE
	@PostMapping("/add")
	public ResponseEntity<?> addAddress(@RequestBody Address address) {
		if (service.addAddress(address)) {
			return new ResponseEntity<>("Created address: " + address, HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Failed to create address: " + address, HttpStatus.NOT_ACCEPTABLE);
	}

	// READ
	// summary -> brief description of API, description -> verbose description of
	// API
	@Operation(summary = "Get all the addresses", description = "Gets all the students from the student table in the spring_db database. Each student grabbed has an id, title, author, and date it was published.")
	@GetMapping("/all")
	public ResponseEntity<?> getAllAddresses() {
		return ResponseEntity.status(200).body(service.getAllAddresses());
	}

	// UPDATE

	@PutMapping("/update")
	public ResponseEntity<ResponseEntity<Address>> updateAddress(@RequestBody Address address)
			throws ResourceNotFoundException {
		return ResponseEntity.status(200).body(service.updateAddress(address));

	}

	// DELETE
	@DeleteMapping("/delete")
	public String deletAddress(@RequestParam Long id) throws ResourceNotFoundException {
		return service.deletAddress(id);
	}

	@GetMapping("/addressid/{id}")
	public Optional<Address> getUserById(@PathVariable("id") Long id) throws ResourceNotFoundException {
		return service.getAddressById(id);
	}
}
