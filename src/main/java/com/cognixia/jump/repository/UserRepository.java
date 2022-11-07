package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByEmail(String email);

	//Name rule 
	Optional<User> findByUsername(String username);

	
	  @Query("select u from User u where u.email = ?1")
	  User findByEmailAddress(String email);
	  
		
}
