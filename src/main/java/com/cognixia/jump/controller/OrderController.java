package com.cognixia.jump.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.OutOfStockException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.OrderItem;
import com.cognixia.jump.service.OrderItemService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderItemService service;
	
	@PostMapping("/add-order-table")
	public ResponseEntity<ResponseEntity<OrderItem>> addProductToOrderTable(@RequestParam("pid") Long pid, @RequestParam("qty") int qty) throws ResourceNotFoundException, OutOfStockException{
	
		return ResponseEntity.status(201).body(service.addProductToOrderItem(pid, qty));
	}
	

	@GetMapping("/all")
	public ResponseEntity<?> getAllOrders() {

		return ResponseEntity.status(200).body(service.getAllOrders());

	}
	


}
