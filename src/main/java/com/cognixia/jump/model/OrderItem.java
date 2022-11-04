package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", unique=true, nullable=false)
	private Long id;
	
	
	@Column(nullable = false )
	private int quantity;
	
	@Column(nullable = false )
	private Date created_at;
	
	
	@Column(nullable = false )
	private Date modified_at;
	
	//Many to Many with Product
	@JsonIgnoreProperties("orders")
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "orders")
	private Set<Product> products = new HashSet<>();

	
	


	//Many To Many with user
	@JsonIgnoreProperties("orders")
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "user_order",
			joinColumns = {@JoinColumn(name = "order_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")}
	)
	private Set<User> users = new HashSet<>();


	public OrderItem() {
		
	}
	public OrderItem(Long id, int quantity, Date created_at, Date modified_at, Set<User> users) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.users = users;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Date getCreated_at() {
		return created_at;
	}


	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public Date getModified_at() {
		return modified_at;
	}


	public void setModified_at(Date modified_at) {
		this.modified_at = modified_at;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", created_at=" + created_at + ", modified_at="
				+ modified_at + ", users=" + users + "]";
	}
	
	
	

}
