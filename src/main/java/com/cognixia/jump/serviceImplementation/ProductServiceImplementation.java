package com.cognixia.jump.serviceImplementation;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Product;
import com.cognixia.jump.repository.ProductRepository;
import com.cognixia.jump.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {

	@Autowired
	ProductRepository repo;

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Product addProduct(Product product) {

		return repo.save(product);
	}

	@Override
	public Optional<Product> getProductById(Long id) {

		return repo.findById(id);
	}

	@Override
	public boolean deleteProductById(Long id) {

		repo.deleteById(id);

		return true;

	}

	@Override
	public Product updateProduct(@Valid Product product) throws ResourceNotFoundException {
		
			
		if(repo.findById(product.getId()).get() != null) {
			System.out.print("Get product " + product);
			return repo.save(product);
		}
		 throw new ResourceNotFoundException(product);
	}

	@Override
	public List<Product> findByNameLike(String name) {
		
		return repo.findByNameLike(name);
	}

}
