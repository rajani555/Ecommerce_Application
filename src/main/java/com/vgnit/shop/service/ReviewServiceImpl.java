package com.vgnit.shop.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vgnit.shop.dto.ReviewDto;
import com.vgnit.shop.entity.Review;
import com.vgnit.shop.entity.User;
import com.vgnit.shop.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService 
{
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public Review saveReview(ReviewDto reviewDto, Principal principal) 
	{
		// TODO Auto-generated method stub
		Review review = new Review();
		
		review.setWriteReview(reviewDto.getWriteReview());
		
		String email = principal.getName();
		User userFound = userService.checkEmail(email);
		review.setUser(userFound);
		
		review.setProduct(productService.getOrEditProductById(reviewDto.getProductId()));
		review.setCreatedAt(LocalDateTime.now());
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> listOfReview() 
	{
		// TODO Auto-generated method stub
		return reviewRepository.findAll();
	}

	@Override
	public String deleteReview(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Review getOrEditReviewById(Long id) 
	{
		// TODO Auto-generated method stub
		Optional<Review> findById = reviewRepository.findById(id);
		Review review = findById.get();
		return review;
	}

	@Override
	public List<Review> findAllReviewByproductId(Long id) 
	{
		// TODO Auto-generated method stub
		List<Review> findAllReviewByproductId = reviewRepository.findAllByproductId(id);
		return findAllReviewByproductId;
	}

}
