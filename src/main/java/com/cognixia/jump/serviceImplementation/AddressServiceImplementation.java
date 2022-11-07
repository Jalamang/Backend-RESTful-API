package com.cognixia.jump.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Address;
import com.cognixia.jump.repository.AddressRepository;
import com.cognixia.jump.service.AddressService;

@Service
public class AddressServiceImplementation implements AddressService {

	@Autowired
	AddressRepository repo;

	@Override
	public boolean addAddress(Address address) {

		repo.save(address);

		if (repo.existsByStreetNumberAndStreetNameAndSuiteNumberAndZipPostalCode(address.getStreetNumber(),
				address.getStreetName(), address.getSuiteNumber(), address.getZipPostalCode())) {
			return true;
		}

		return false;
	}

	@Override
	public List<Address> getAllAddresses() {
		return repo.findAll();
	}

	@Override
	public ResponseEntity<Address> updateAddress(Address address) throws ResourceNotFoundException {

		if (repo.findById(address.getId()) != null) {

			repo.save(address);
		}
		return ResponseEntity.status(200).body(address);
	}

	@Override
	public String deletAddress(Long id) throws ResourceNotFoundException {

		repo.deleteById(id);
		return "Address with id " + id + " is removed";
	}

	@Override
	public Optional<Address> getAddressById(Long id) throws ResourceNotFoundException {

		if (repo.findById(id) != null) {

			return repo.findById(id);
		}
		throw new ResourceNotFoundException("Address with id " + id + " not found");

	}

}
