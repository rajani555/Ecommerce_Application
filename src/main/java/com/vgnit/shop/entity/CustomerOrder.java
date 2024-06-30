package com.vgnit.shop.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "customer_order")
public class CustomerOrder 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	@JsonIgnore
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonIgnore
	private Product product;
	
	private Long customerOrderID;
	
	private LocalDateTime createdAt;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime localDateTime) {
		this.createdAt = localDateTime;
	}

	public Long getCustomerOrderID() {
		return customerOrderID;
	}

	public void setCustomerOrderID(Long customerOrderID) {
		this.customerOrderID = customerOrderID;
	}

	public CustomerOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerOrder(Long id, User user, Address address, Product product, LocalDateTime createdAt, Long customerOrderID) {
		super();
		this.id = id;
		this.user = user;
		this.address = address;
		this.product = product;
		this.createdAt = createdAt;
		this.customerOrderID = customerOrderID;
	}

}
