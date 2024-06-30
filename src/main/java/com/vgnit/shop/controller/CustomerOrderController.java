package com.vgnit.shop.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vgnit.shop.dto.CustomerOrderDTO;
import com.vgnit.shop.entity.CustomerOrder;
import com.vgnit.shop.entity.User;
import com.vgnit.shop.service.CategoryService;
import com.vgnit.shop.service.CustomerOrderService;
import com.vgnit.shop.service.UserService;

import ForAll.UniversalData;

@Controller
public class CustomerOrderController 
{
	@Autowired
	private CustomerOrderService customerOrderService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	// (1- Direct order)
	// For BuyNow product Customer order !
	@PostMapping("/Order/addBuyNowCustomerOrder")
	public String addBuyNowCustomerOrder(@ModelAttribute("customerOrderDTO") CustomerOrderDTO customerOrderDTO, Principal principal, RedirectAttributes redirAttrs)
	{
		CustomerOrder saveCustomerOrder = customerOrderService.saveBuyNowCustomerOrder(customerOrderDTO, principal);
		if(saveCustomerOrder!=null)
		{
			 redirAttrs.addFlashAttribute("message", "Your order placed successfully!");
		}
		return "redirect:/";
	}

	
	// (2- Cart items order)
	// For Cart items product Customer order !
	@PostMapping("/Order/addCartItemsCustomerOrder")
	public String addCartItemsCustomerOrder(@ModelAttribute("customerOrderDTO") CustomerOrderDTO customerOrderDTO, Principal principal, RedirectAttributes redirAttrs)
	{
		List<CustomerOrder> saveCustomerOrder = customerOrderService.saveCartItemsCustomerOrder(customerOrderDTO, principal);
		if(saveCustomerOrder!=null)
		{
			 redirAttrs.addFlashAttribute("message", "Your order placed successfully!");
		}
		return "redirect:/";
	}
	
	
	
	@GetMapping("/admin/Order/listOfOrder")
	public String listOfOrderForAdmin(Model model)
	{
		List<CustomerOrder> listOfCustomerOrder = customerOrderService.listOfCustomerOrder();
		model.addAttribute("listOfCustomerOrder", listOfCustomerOrder);
		
		return "order-list-admin";
	}
	
	@GetMapping("/Order/listOfOrder")
	public String listOfOrderForUser(Model model, Principal principal)
	{
//		List of CustomerOrder on the basis of (CustomerOrder primary id), whether user currently login or not that period, actually it return all the saved CustomerOrder list !!
//		List<CustomerOrder> listOfCustomerOrder = customerOrderService.listOfCustomerOrder();
//		model.addAttribute("listOfCustomerOrder", listOfCustomerOrder);
		
// 		List of CustomerOrder on the basis of who currently login that moment, that means user id, By the way it return only authenticated user CustomerOrder !!
		String email = principal.getName();
		User user = userService.checkEmail(email);
		model.addAttribute("user", user);
		Long id = user.getId();
		
// 		List of CustomerOrder on the basis of who currently login that moment, that means user id, By the way it return only authenticated user CustomerOrder !!
		List<CustomerOrder> listOfCustomerOrder = customerOrderService.listOfCustomerOrderById(id);
		
		// Step for reverse the order list to display last item at the top positions (like: Original list: [1, 2, 3, 4, 5] , Reversed list: [5, 4, 3, 2, 1]) Using Collections.reverse() method to reverse the list
		Collections.reverse(listOfCustomerOrder);
		model.addAttribute("listOfCustomerOrder", listOfCustomerOrder);
		
		return "order-list-user";
	}
	
	
	
	
	@GetMapping("/Order/listOfOrder2")
	public String listOfOrderForUser2(Model model, Principal principal)
	{
		// List of all categories!
		model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
				
//		List of CustomerOrder on the basis of (CustomerOrder primary id), whether user currently login or not that period, actually it return all the saved CustomerOrder list !!
//		List<CustomerOrder> listOfCustomerOrder = customerOrderService.listOfCustomerOrder();
//		model.addAttribute("listOfCustomerOrder", listOfCustomerOrder);
		
// 		List of CustomerOrder on the basis of who currently login that moment, that means user id, By the way it return only authenticated user CustomerOrder !!
		String email = principal.getName();
		User user = userService.checkEmail(email);
		model.addAttribute("user", user);
		Long id = user.getId();
		
// 		List of CustomerOrder on the basis of who currently login that moment, that means user id, By the way it return only authenticated user CustomerOrder !!
		List<CustomerOrder> listOfCustomerOrder = customerOrderService.listOfCustomerOrderById(id);
		
		// Step for reverse the order list to display last item at the top positions (like: Original list: [1, 2, 3, 4, 5] , Reversed list: [5, 4, 3, 2, 1]) Using Collections.reverse() method to reverse the list
		Collections.reverse(listOfCustomerOrder);
		model.addAttribute("listOfCustomerOrder", listOfCustomerOrder);
		
		// Cart view!
		model.addAttribute("cartCountValue", UniversalData.cart.size());   //Attention Here !!
		
		return "order";
	}
	
	@GetMapping("/Order/listOfOrderDetails2/{id}")
	public String listOfOrderDetailsForUser2(@PathVariable(value = "id") Long id, Model model, Principal principal)
	{
		CustomerOrder particularOrderByIdFound = customerOrderService.getParticularCustomerOrderById(id);
		model.addAttribute("OrderByIdFound", particularOrderByIdFound);
		
		// List of all categories!
		model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
		
		// Cart view!
		model.addAttribute("cartCountValue", UniversalData.cart.size());   //Attention Here !!
		
		// Already logged principle details !!
		String email = principal.getName();
		User user = userService.checkEmail(email);
		model.addAttribute("user", user);

		return "order_details";
	}
	
}
