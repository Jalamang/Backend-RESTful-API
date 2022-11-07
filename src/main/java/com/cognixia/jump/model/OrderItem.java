package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "order_id", unique = true, nullable = false)
	private Long id;

	@Column(nullable = false)
	private int quantity;

	@Column(nullable = false)
	private Date created_at;

	@Column(nullable = false)
	private Date modified_at;

	// Link for 1 to Many to user
	@JsonIgnoreProperties("orders")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnoreProperties("orders")
	@ManyToOne
	@JoinColumn(name = "product_id")
	Product product;

	public OrderItem() {

	}



	public OrderItem(Long id, int quantity, Date created_at, Date modified_at, User user, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.user = user;
		this.product = product;
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



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", created_at=" + created_at + ", modified_at="
				+ modified_at + ", user=" + user + ", product=" + product + "]";
	}

}
