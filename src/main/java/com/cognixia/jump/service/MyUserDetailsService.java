package com.cognixia.jump.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	
	@Autowired
	UserRepository UserRepo;
	
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		

//		
//		// only look for a user with username = user1 and password = pw123
//		
//		if( !username.equals("user1") ) {
//			throw new UsernameNotFoundException("User with username = " + username + " not found");
//		}
//		
//		return new User("user1", "pw123", Arrays.asList( new SimpleGrantedAuthority("ROLE_USER") ));
//	}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user =UserRepo.findByUsername(username);
		
//		User result  = user.orElseThrow(() -> new UsernameNotFoundException("User with username = " + username + " not found"));
		// only look for a user with username = user1 and password = pw123
		
		if( username.isEmpty() ) {
			throw new UsernameNotFoundException("User with username = " + username + " not found");
		}
		
		return new MyUserDetails(user.get());
	}

}
