package com.cognixia.jump.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.OutOfStockException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.OrderItem;

@Service
public interface OrderItemService {
	public ResponseEntity<OrderItem> addProductToOrderItem(Long pid, int qty) throws ResourceNotFoundException, OutOfStockException;

	public List<OrderItem> getAllOrders();

}
