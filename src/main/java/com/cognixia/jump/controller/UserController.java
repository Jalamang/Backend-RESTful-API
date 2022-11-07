package com.cognixia.jump.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.DuplicateResourceException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService service;


	// CREATE
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody @Valid User user) throws DuplicateResourceException {

		return ResponseEntity.status(201).body(service.addUser(user));

	}

	@GetMapping("/getuser-byemail")
	public ResponseEntity<ResponseEntity<User>> findByEmailAddress(@RequestParam("email") String email){
		return ResponseEntity.status(201).body(service.findByEmailAddress(email));
	}
	
	
	// READ
//	 summary -> brief description of API, description -> verbose description of API
	@Operation(summary = "Gets all the users", description = "Gets all the users from the user table in the spring_db database...")
	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.status(200).body(service.getAllUsers());
	}


	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletUser(@PathVariable Long id) {
		
		return ResponseEntity.status(200).body(service.deletUser(id));
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return service.getUserById(id);
	}

	@PutMapping("/update-user")
	public ResponseEntity<ResponseEntity<User>> updateUser(@Valid @RequestBody User user)
			throws UsernameNotFoundException {

		return ResponseEntity.status(200).body(service.updateUser(user));

	}

}
