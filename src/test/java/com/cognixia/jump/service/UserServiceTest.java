package com.cognixia.jump.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognixia.jump.model.Address;
import com.cognixia.jump.model.OrderItem;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	// @Mock set up mock object, to allow us to mock the repository
	@Mock
	private UserRepository repo;


//	 User(Long id, @Size(min = 5, max = 15) String username, String password,
//				@Email(message = "Not a valid email format.") String email, Role role, boolean enabled, Address address,
//				Set<OrderItem> orders) 
	@InjectMocks
	private UserService service;
	
	@Test
	void testGetAllStudents() throws Exception {
		// arrange -> get all info needed
//		List<User> users = new ArrayList<>();
//		users.add(new User(1L, "user1", "password", "user1@gmail.com", "ROLE_USER", 1, new Address(), new OrderItem(null, null, 0, null, null, null))
//		
//
//		when(repo.findAll()).thenReturn(students);
//		// Act -> perform the test
//		List<Student> result = service.getAllStudents();
//
//		// Assert -> did the test pass ?
//
//		if (students.size() != result.size()) {
//			fail();
//		}
//		for (int i = 0; i < students.size(); i++) {
//			Student original = students.get(i);
//			Student retrieved = result.get(i);
//
//			if (!original.equals(retrieved)) {
//
//				fail();
//			}
//		}

	}
	
}
