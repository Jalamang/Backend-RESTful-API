package com.cognixia.jump.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.DuplicateResourceException;
import com.cognixia.jump.model.User;

@Service
public interface UserService {

	public List<User> getAllUsers();

	public ResponseEntity<?> addUser(User user) throws DuplicateResourceException;

	public User getUserById(Long id);

	public String deletUser(Long id);

	public boolean addAddressToUser(Long addressId, Long userId);

	public ResponseEntity<User> updateUser(User user) throws UsernameNotFoundException;

	public ResponseEntity<User> findByEmailAddress(String email);
}
