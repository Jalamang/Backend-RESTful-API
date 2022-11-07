package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Address;

public interface AddressService {

	// adds address
	public boolean addAddress(Address address);

	// Gets all the address
	public List<Address> getAllAddresses();

	// Update address
	public ResponseEntity<Address> updateAddress(Address address) throws ResourceNotFoundException;

	// Delete address
	public String deletAddress(Long id) throws ResourceNotFoundException;

	// Get address by id
	Optional<Address> getAddressById(Long id) throws ResourceNotFoundException;

}
