package com.vgnit.shop.service;

import java.security.Principal;
import java.util.List;
import com.vgnit.shop.dto.ReviewDto;
import com.vgnit.shop.entity.Review;

public interface ReviewService 
{
	public Review saveReview(ReviewDto reviewDto, Principal principal);
	
	public List<Review> listOfReview();
	
	public String deleteReview(Long id);
	
	public Review getOrEditReviewById(Long id);
	
	public List<Review> findAllReviewByproductId(Long id);
}
