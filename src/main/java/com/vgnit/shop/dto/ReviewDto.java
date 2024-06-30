package com.vgnit.shop.dto;
import java.time.LocalDate;

public class ReviewDto 
{
	private Long id;
	private String writeReview;
	
	// Attention Here !
	private Long productId;
	
	// Attention Here !
	private Long userId;
	
	private LocalDate createdAt;
	

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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public ReviewDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewDto(Long id, String writeReview, Long productId, Long userId, LocalDate createdAt) {
		super();
		this.id = id;
		this.writeReview = writeReview;
		this.productId = productId;
		this.userId = userId;
		this.createdAt = createdAt;
	}
	
}
