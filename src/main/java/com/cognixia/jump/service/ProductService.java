package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Product;

@Service
public interface ProductService {

	List<Product>  getProducts();

	public Product addProduct(Product product);

	public  Optional<Product> getProductById(Long id);

	public boolean deleteProductById(Long id);

	public Product updateProduct(@Valid Product product) throws ResourceNotFoundException;
	
	 List<Product> findByNameLike(String name);
	
}
