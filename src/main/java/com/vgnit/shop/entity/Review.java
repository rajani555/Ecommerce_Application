package com.vgnit.shop.entity;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review 
{
	// ZO-RAJANI
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String writeReview;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonIgnore
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWriteReview() {
		return writeReview;
	}

	public void setWriteReview(String writeReview) {
		this.writeReview = writeReview;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(Long id, String writeReview, Product product, User user, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.writeReview = writeReview;
		this.product = product;
		this.user = user;
		this.createdAt = createdAt;
	}
	
}
