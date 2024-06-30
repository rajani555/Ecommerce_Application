package com.vgnit.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vgnit.shop.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>
{
	// See Attention Here !!
	List<Review> findAllByproductId(Long id);
}
