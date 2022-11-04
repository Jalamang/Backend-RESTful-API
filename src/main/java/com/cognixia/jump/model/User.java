package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static enum Role {
		ROLE_USER, ROLE_ADMIN
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique=true, nullable=false)
	private Long id;
	
	
	@Size(min = 5, max = 15)
	@Column(updatable = false, nullable = false, unique = true, length = 25)
	private String username;

	@Column(updatable = true, nullable = false, length = 25)
	private String password;

	
	@Column(updatable = false, nullable = false, unique = true)
	@Email(message = "Not a valid email format.")
	private String email;

	// will store the role as a string in the db
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(columnDefinition = "boolean default true")
	private boolean enabled;
	
	
	// Link for 1 to Many to address
		@JsonIgnoreProperties("users")
		@ManyToOne
		@JoinColumn(name = "address_id")
		private Address address;
		
	
	//Many to Many with OrderItem
		@JsonIgnoreProperties("users")
		@ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
		private Set<OrderItem> orders = new HashSet<>();

		
		public User(){
			
		}

	public User(Long id, @Size(min = 5, max = 15) String username, String password,
			@Email(message = "Not a valid email format.") String email, Role role, boolean enabled, Address address,
			Set<OrderItem> orders) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.enabled = enabled;
		this.address = address;
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<OrderItem> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderItem> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
				+ role + ", enabled=" + enabled + ", address=" + address + ", orders=" + orders + "]";
	}
		
		
}
