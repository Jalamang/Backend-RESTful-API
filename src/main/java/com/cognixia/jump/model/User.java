package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static enum Role {
		ROLE_USER, ROLE_ADMIN
	}

	
	@Schema(description = "e-Commerce Backend Basics: RESTful API (Spring boot, API, REST, Methods, JSON, MySQL, Hibernate and JPA", example = "Instant", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long id;

	@Size(min = 1, max = 100)
	@Column(updatable = true, nullable = false, unique = true, length = 25)
	private String username;

	@Column(updatable = true, nullable = false, length = 255)
	private String password;
	
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@Column(name="last_name", nullable = false)
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@Email(message = "Not a valid email format.")
	private String email;
	
	@Column(updatable = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(columnDefinition = "boolean default true")
	private boolean enabled;

	// Link for 1 to Many to address
	@JsonIgnoreProperties("users")
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	// one to Many with OrderItem
	@JsonIgnoreProperties("user")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	
	private Set<OrderItem> orders = new HashSet<>();

	public User() {
		this.id = -1L;
		this.username = "N/A";
		this.password = "N/A";
		this.firstName = "N/A";
		this.lastName = "N/A";
		this.email = "N/A";
		this.role = null;
		this.enabled = true;
		this.address = null;
		this.orders = null;
	}

	public User(Long id, @Size(min = 1, max = 100) String username, String password, String firstName, String lastName,
			@Email(message = "Not a valid email format.") String email, Role role, boolean enabled, Address address,
			Set<OrderItem> orders) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", role=" + role + ", enabled=" + enabled
				+ ", address=" + address + ", orders=" + orders + "]";
	}

}
