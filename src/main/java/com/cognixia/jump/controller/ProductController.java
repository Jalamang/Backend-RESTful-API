package com.cognixia.jump.controller;

import java.util.List;

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

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Product;
import com.cognixia.jump.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService service;

	@GetMapping("/all")
	public ResponseEntity<?> getProducts() {

		return ResponseEntity.status(200).body(service.getProducts());

	}
	
	@GetMapping("/by-name")
	public ResponseEntity<List<Product>> findByNameLike(@RequestParam("name") String name){
		return ResponseEntity.status(200).body(service.findByNameLike(name));
	}

	@GetMapping("/product-byid")
	public ResponseEntity<?> getProductById(@RequestParam("id") Long id) {

		return ResponseEntity.status(200).body(service.getProductById(id));

	}

	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
		return ResponseEntity.status(200).body(service.deleteProductById(id));
	}

	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {

		return ResponseEntity.status(200).body(service.addProduct(product));

	}

	@PutMapping("/update-product")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product)
			throws UsernameNotFoundException, ResourceNotFoundException {

		return ResponseEntity.status(200).body(service.updateProduct(product));

	}
	
	
}
