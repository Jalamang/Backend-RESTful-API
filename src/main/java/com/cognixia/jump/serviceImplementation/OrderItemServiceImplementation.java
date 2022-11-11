package com.cognixia.jump.serviceImplementation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.OutOfStockException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.OrderItem;
import com.cognixia.jump.model.Product;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.OrderItemRepository;
import com.cognixia.jump.repository.ProductRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.OrderItemService;

@Service
public class OrderItemServiceImplementation implements OrderItemService {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	OrderItemRepository orderRepo;

	@Autowired
	UserRepository userRepo;

//	@Override
	public ResponseEntity<OrderItem> addProductToOrderItem(Long pid, int qty)
			throws ResourceNotFoundException, OutOfStockException {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = "";

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		Optional<User> currentUser = userRepo.findByUsername(username);

		Optional<Product> product = productRepo.findById(pid);

		if (qty > product.get().getQuantity()) {

			throw new OutOfStockException(product.get().getName() + " is out of stock ");
		}

		if ((currentUser.isPresent() && product.isPresent())) {

//			int qtyAval = product.get().getQuantity();
//
//			Product p = product.get();
//			p.setQuantity(qtyAval - qty);
//
//			productRepo.save(p);
			
			product.get().setQuantity(product.get().getQuantity() - qty);

			productRepo.save(product.get());

			return ResponseEntity.status(201).body(
					orderRepo.save(new OrderItem(pid, qty, new Date(), new Date(), currentUser.get(), product.get())));
		}
		throw new ResourceNotFoundException(
				" Either product with id " + pid + ", user " + currentUser + " is not found ");

	}

//	@Override
	public List<OrderItem> getAllOrders() {

		return orderRepo.findAll();
	}

}
