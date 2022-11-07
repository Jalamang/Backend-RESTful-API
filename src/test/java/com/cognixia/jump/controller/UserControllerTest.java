package com.cognixia.jump.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserService;

//Annotate the test class with a corresponding SRC code Class
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {


	// We need to account for all SRC code dependencies injected into the class we
	// annotated above
	// ie. StudentController.java

	// @MockBean -> mock the method calls for the service & repo
	// -> when method attempts to run, we'll tell it what to do
	@MockBean
	UserService service;

	@MockBean
	UserRepository repo;

	// when the controller tries to autowire the service or repo,
	// mock the creation of those classes
	@InjectMocks
	UserController controller;

	// mocking the request / response
	@Autowired
	MockMvc mockMvc;
	
	
//	@WithMockUser(username="johnm")

	
//	@Test
//	void testGetAllUsers() throws Exception {
//
//		// test data returned in the body
//		Set<OrderItem> orders = new TreeSet<OrderItem>();
//		
//
////		User user = new User(1L, "johnm", "password", "Lamin", "Sarr", "user1@gmail.com", null, true, null, orders);
//
//		Address address = new Address(1L, "507", "1 Leman St", "207", "NYC", "Bronx", "1001-010", "USA", null);
//		TreeSet<User> users = new TreeSet<User>();
//
//		users.add( new User(1L, "johnm", "password", "Lamin", "Sarr", "user1@gmail.com", null, true, address, orders));
//		
//
//		String uri = "/users/all";
//
//	
//		when(service.getAllUsers()).thenReturn(users);
////
////		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk())
////				.andExpect(jsonPath("$.length()").value(students.size())) // check size of resulting array
////				.andExpect(jsonPath("$[0].id").value(students.get(0).getId())) // checks if first student has id = 1
////				.andExpect(jsonPath("$[1].lastName").value(students.get(1).getLastName())) // checks second student has
////																							// lastName = platypus
////				.andExpect(jsonPath("$[0].user.id").value(students.get(0).getUser().getId()));
////
////		// verify can check how many times a method is called during a test
////		verify(service, times(1)).getAllStudents(); // check this method is only called once
////
////		// make sure no more methods from service are being called
////		verifyNoMoreInteractions(service);
//
//	}

	
	
}
