package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private Long id;

//	@Schema(description = "Kudang Primary School", example = "Primary School", required = true)
	@Size(min = 1, max = 100)
	@Column(nullable = false, length = 10)
	private String streetNumber;

//	@Schema(description = "Kudang Primary School", example = "Primary School", required = true)
	@Size(min = 1, max = 100)
	@Column(nullable = false, length = 30)
	private String streetName;

//	@Schema(description = "Kudang Primary School", example = "Primary School", required = true)
	@Size(min = 1, max = 100)
	@Column(nullable = true, length = 10)
	private String suiteNumber;

//	@Schema(description = "Kudang Primary School", example = "Primary School", required = true)
	@Size(min = 1, max = 100)
	@Column(nullable = false, length = 30)
	private String city;

//	@Schema(description = "Kudang Primary School", example = "Primary School", required = true)
	@Size(min = 1, max = 100)
	@Column(nullable = true, length = 30)
	private String stateOrProvince;

	@Column(nullable = false, length = 10)
	private String zipPostalCode;

	@Column(nullable = false, length = 30)
	private String country;

	// Link for the 1 to Many of user
	@JsonIgnoreProperties("address")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.ALL)
	private Set<User> users = new HashSet<>();

	public Address() {
		this.id = -1L;
		this.streetNumber = "N/A";
		this.streetName = "N/A";
		this.suiteNumber = "N/A";
		this.city = "N/A";
		this.stateOrProvince = "N/A";
		this.zipPostalCode = "N/A";
		this.country = "N/A";
		this.users = null;
	}

	public Address(Long id, String streetNumber, String streetName, String suiteNumber, String city,
			String stateOrProvince, String zipPostalCode, String country, Set<User> users) {
		super();
		this.id = id;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.suiteNumber = suiteNumber;
		this.city = city;
		this.stateOrProvince = stateOrProvince;
		this.zipPostalCode = zipPostalCode;
		this.country = country;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getSuiteNumber() {
		return suiteNumber;
	}

	public void setSuiteNumber(String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateOrProvince() {
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	public String getZipPostalCode() {
		return zipPostalCode;
	}

	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		this.users.add(user);
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", streetNumber=" + streetNumber + ", streetName=" + streetName + ", suiteNumber="
				+ suiteNumber + ", city=" + city + ", stateOrProvince=" + stateOrProvince + ", zipOrPostalCode="
				+ zipPostalCode + ", country=" + country + ", students=" + users + "]";
	}

}
