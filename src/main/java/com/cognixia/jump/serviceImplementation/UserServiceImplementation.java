package com.cognixia.jump.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.DuplicateResourceException;
import com.cognixia.jump.model.Address;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.AddressRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public List<User> getAllUsers() {

		// returns all users
		return userRepo.findAll();
	}

	public ResponseEntity<?> addUser(User user) throws DuplicateResourceException {

		user.setId(null);

		if (userRepo.findUserByEmail(user.getEmail()).isPresent()) {
			throw new DuplicateResourceException(user.getEmail());
		}

		// each password for a new user gets encoded
		user.setPassword(encoder.encode(user.getPassword()));

		return ResponseEntity.status(201).body(userRepo.save(user));
	}

	@Override
	public User getUserById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public String deletUser(Long id) {
		userRepo.deleteById(id);
		return "User with id " + id + " is removed";
	}

	@Override
	public boolean addAddressToUser(Long addressId, Long userId) {
		Optional<Address> addressAdd = addressRepo.findById(addressId);
		Optional<User> userAdd = userRepo.findById(userId);

		if (addressAdd.isPresent() && userAdd.isPresent()) {

			addressAdd.get().addUser(userAdd.get());
			userAdd.get().setAddress(addressAdd.get());

			addressRepo.save(addressAdd.get());
			userRepo.save(userAdd.get());

			return true;
		}

		return false;
	}

//	@Override
	public ResponseEntity<User> updateUser(User user) throws UsernameNotFoundException {
		user.setPassword(encoder.encode(user.getPassword()));

		User existingUser = userRepo.findById(user.getId()).orElse(null);
		existingUser.setUsername(user.getUsername());
		existingUser.setPassword(user.getPassword());
		existingUser.setEmail(user.getEmail());
		existingUser.setRole(user.getRole());
		existingUser.setEnabled(user.isEnabled());

		return ResponseEntity.status(200).body(userRepo.save(existingUser));
	}

	@Override
	public ResponseEntity<User> findByEmailAddress(String email) {
		// TODO Auto-generated method stub
		return ResponseEntity.status(200).body(userRepo.findByEmailAddress(email));
	}

}
