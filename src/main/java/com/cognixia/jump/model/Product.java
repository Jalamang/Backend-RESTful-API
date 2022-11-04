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
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique=true, nullable=false)
	private Long id;
	
	@Column(nullable = false )
	private String name;
	
	@Column(nullable = false )
	private String price;
	
	@Column(nullable = false )
	private String descritpion;
	
	@Column(nullable = false )
	private Date created_at;
	
	
	@Column(nullable = false )
	private Date modified_at;

	
	//Many To Many with OrderItem
	@JsonIgnoreProperties("products")
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "order_product",
			joinColumns = {@JoinColumn(name = "product_id")},
			inverseJoinColumns = {@JoinColumn(name = "order_id")}
	)
	private Set<OrderItem> orders = new HashSet<>();

	public Product() {
		
	}
			
	

	public Product(Long id, String name, String price, String descritpion, Date created_at, Date modified_at,
			Set<OrderItem> orders) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.descritpion = descritpion;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.orders = orders;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescritpion() {
		return descritpion;
	}

	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", descritpion=" + descritpion
				+ ", created_at=" + created_at + ", modified_at=" + modified_at + "]";
	}
	
	

}
