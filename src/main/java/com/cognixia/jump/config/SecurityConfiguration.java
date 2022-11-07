package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognixia.jump.filter.JwtRequestFilter;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter ;
	// Authentication
	@Bean
	protected UserDetailsService userDetailsService() {
		
		return userDetailsService;
	}
	
	
	private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

	// Authorization  
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/users/add", "/authenticate", "/orders/**").permitAll()
			.antMatchers("/product/add", "/product/update-product", "/product/delete/{id}")
			.access("hasRole('ADMIN')")
			.antMatchers(AUTH_WHITELIST).permitAll()  // whitelist Swagger UI resources
			.anyRequest().authenticated()	// all APIs, you have to have a user account
			.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler())  //redirects to /access/denied URI
			.and()     
			.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ); // tell spring security to NOT create sessions
	
//		https://www.javadevjournal.com/spring-security/spring-security-custom-403-access-denied-page/	
//		//Request will go thru many filters, but typically the FIRST filter it checks is the on for username and password
//		//howeever, we will set it up, that our JWT filter gets check first, or else the authentication will fail, since spring security won't, 
//		//know where to find username and password
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	protected PasswordEncoder encoder() {

		// plain text encoder -> encode/encrypt the password
//		return NoOpPasswordEncoder.getInstance();
		
		// encrypt the password with the bcrypt algorithm
		return new BCryptPasswordEncoder();
}
	// load in the encoder & user details service that are needed for security to do authentication & authorization
	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); 

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder( encoder() );
		
		return authProvider;
	}
	
	
	// can autowire and access the authentication manager (manages authentication (login) of our project)
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	
	//redirect to access denied url
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new CustomAccessDeniedHandler();
	}
}















