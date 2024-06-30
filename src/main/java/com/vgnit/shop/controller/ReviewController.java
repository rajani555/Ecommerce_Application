package com.vgnit.shop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vgnit.shop.dto.ReviewDto;
import com.vgnit.shop.entity.Product;
import com.vgnit.shop.entity.Review;
import com.vgnit.shop.entity.User;
import com.vgnit.shop.repository.UserRepository;
import com.vgnit.shop.service.CategoryService;
import com.vgnit.shop.service.ProductService;
import com.vgnit.shop.service.ReviewService;

import ForAll.UniversalData;

@Controller
public class ReviewController 
{
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/review/getReviewPage/{id}")
	public String getReviewPageById(@PathVariable(value = "id") Long id, Model model, Principal principal)
	{
		ReviewDto reviewDto = new ReviewDto();
		model.addAttribute("reviewDto", reviewDto);
		
		Product getorEditProduct = productService.getOrEditProductById(id);  		// This is return  all  product details !!
		Long productIdFound = getorEditProduct.getId();							 	// And here we get product id only...we can get all details !!
		model.addAttribute("productIdFound", productIdFound);				  		// And here we send above product id only on front-end...we can send all details on front-end!!
			
		model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
		model.addAttribute("cartCountValue", UniversalData.cart.size());   	  		//Attention Here !!
		
		// Simply list of all review !!
		List<Review> listOfReview = reviewService.listOfReview();
		model.addAttribute("listOfReview", listOfReview);
		
		// Attention Here !!
		// Getting all list of review by particular product id!!
		// Own custom method !!
		// This model is send on HomeController at details page ...please see!...yahaa par iska humane koi use nahi kiya hai, just for checking concept.
		List<Review> reviewById = reviewService.findAllReviewByproductId(id);
		model.addAttribute("reviewById", reviewById);

		if (principal != null)
		{
			String email = principal.getName();
			User user = userRepository.findByEmail(email);
			model.addAttribute("user", user);
		}
			
		return "add-review";
	}
	
	
	@PostMapping("/review/addReview")
	public String addReview(@ModelAttribute("reviewDto") ReviewDto reviewDto, Principal principal, RedirectAttributes redirectAttributes)
	{
		reviewService.saveReview(reviewDto, principal);
		redirectAttributes.addFlashAttribute("message", "Review added");
		
//		return "redirect:/";
		
		return "redirect:/review/getReviewPage/" + reviewDto.getProductId();
	}
	
}
