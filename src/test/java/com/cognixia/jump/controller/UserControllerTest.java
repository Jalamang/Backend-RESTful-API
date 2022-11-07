package com.cognixia.jump.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserService;
import org.springframework.http.MediaType;

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
	
	@Test
	public void testGetAllUsers() throws Exception {

		String uri = "/users/all";

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	
}
