package com.vgnit.shop.controller;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vgnit.shop.dto.AddressDTO;
import com.vgnit.shop.dto.CustomerOrderDTO;
import com.vgnit.shop.entity.Address;
import com.vgnit.shop.entity.Product;
import com.vgnit.shop.entity.User;
import com.vgnit.shop.service.AddressService;
import com.vgnit.shop.service.ProductService;
import com.vgnit.shop.service.UserService;

import ForAll.UniversalData;
import ForAll.UniversalData_2;
import ForAll.UniversalData_3;

@Controller
public class CartController 
{
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	
	// ADD ITEMS INTO CART...!
	@GetMapping("/home/addToCart/{id}")
	public String addToCart(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes)
	{
		UniversalData.cart.add(productService.getOrEditProductById(id));
		
//		FLASH MESSAGE WITH TOASTR MODE !!
		redirectAttributes.addFlashAttribute("message", "If item added");
		
		 
//		FLASH MESSAGE WITH THYMELEAF NORMAL MODE !!
//		redirectAttributes.addFlashAttribute("message", "Item successfully added to cart");
//		redirAttrs.addFlashAttribute("alertClass", "alert-success");
		
		return "redirect:/home/viewCart";
	}
	
	/*
	// If you use this method then you have to add some if and else condition see below... for returning two different page in different situation like...
	// first condition when cart is not-empty and another is when cart is empty too.
	@GetMapping("/home/viewCart")
	public String getCartDetails(Model model)
	{
		int size = UniversalData.cart.size();
		if(size!=0)
		{
			model.addAttribute("cartCountValue", UniversalData.cart.size());
			model.addAttribute("totalCartAmount", UniversalData.cart.stream().mapToDouble(Product::getPrice).sum());
			model.addAttribute("totalDiscAmount", UniversalData.cart.stream().mapToDouble(Product::getSaveAmount).sum());
			
			model.addAttribute("TotalFinalAmount", UniversalData.cart.stream().mapToDouble(Product::getPrice).sum() - UniversalData.cart.stream().mapToDouble(Product::getSaveAmount).sum());
			
			model.addAttribute("cart", UniversalData.cart);
			return "view-cart";
		}
		else
		{
			return "view-cart-empty";
		}
	}
	*/
	
	// If you use this method then you have not to need add some if and else condition see below... for returning two different page in different situation like...
	// first condition when cart is not-empty and another is when cart is empty two. You can simply use "isEmpty()" method of list on the view page... see on the view at
	// the "view-cart" Front-end page, here we can display both condition (cart is not-empty and cart is empty) with same page, so I AM USING THIS METHOD.
	// VIEW CART ITEMS...!
	@GetMapping("/home/viewCart")
	public String getCartDetailssss(Model model, @Param("keyword") String keyword, @ModelAttribute("message") String Flashmessage)
	{
			model.addAttribute("cartCountValue", UniversalData.cart.size());
			model.addAttribute("totalCartAmount", UniversalData.cart.stream().mapToDouble(Product::getPrice).sum());
			model.addAttribute("totalDiscAmount", UniversalData.cart.stream().mapToDouble(Product::getSaveAmount).sum());
//			model.addAttribute("TotalFinalAmount", UniversalData.cart.stream().mapToDouble(Product::getPrice).sum() - UniversalData.cart.stream().mapToDouble(Product::getSaveAmount).sum());
			
			// This is cart view...!!
			model.addAttribute("cart", UniversalData.cart);						 
			
			// FLASH MESSAGE WITH TOASTR MODE !!
			model.addAttribute("message", Flashmessage);
			
			// This is save for later...!!
			model.addAttribute("saveForLater", UniversalData_2.saveForLater);	  
			model.addAttribute("saveForLaterCountValue", UniversalData_2.saveForLater.size());
			model.addAttribute("listofProduct", productService.listofProduct(keyword));
			
			// This will be recently taken for utilization as delivery consideration !!
			double Delivery_Charges= 0;
			double Delivery_Amount= 40;
			double Delivery_Band= 499;
			double TotalFinalAmount= UniversalData.cart.stream().mapToDouble(Product::getPrice).sum() - UniversalData.cart.stream().mapToDouble(Product::getSaveAmount).sum();
			if(TotalFinalAmount>=1 && TotalFinalAmount<=Delivery_Band)
			{
				Delivery_Charges= Delivery_Charges + Delivery_Amount;
				model.addAttribute("Delivery_Charges", Delivery_Charges);
				model.addAttribute("TotalFinalAmount", UniversalData.cart.stream().mapToDouble(Product::getPrice).sum() - UniversalData.cart.stream().mapToDouble(Product::getSaveAmount).sum() + Delivery_Charges);
			}
			else
			{
				Delivery_Charges= Delivery_Charges + 0;
				model.addAttribute("Delivery_Charges", Delivery_Charges);
				model.addAttribute("TotalFinalAmount", UniversalData.cart.stream().mapToDouble(Product::getPrice).sum() - UniversalData.cart.stream().mapToDouble(Product::getSaveAmount).sum());
			}
			
			return "view-cart";
	}
	
	// REMOVE CART ITEMS...!
	@GetMapping("/home/removeCartItem/{index}")
	public String removeCartItem(@PathVariable(value = "index") int index, RedirectAttributes redirectAttributes)
	{
		UniversalData.cart.remove(index);
		
//		FLASH MESSAGE WITH TOASTR MODE !!
		redirectAttributes.addFlashAttribute("message", "If item removed");
		
		return "redirect:/home/viewCart";
	}
	
	// ADD SAVE FOR LATER ITEMS...!
	@GetMapping("/home/saveLater/{index}")
	public String saveForLater(@PathVariable(value = "index") int index, RedirectAttributes redirectAttributes)
	{
		Product remove = UniversalData.cart.remove(index);
		UniversalData_2.saveForLater.add(remove);
		
//		FLASH MESSAGE WITH TOASTR MODE !!
		redirectAttributes.addFlashAttribute("message", "If item saved for later");
		
		return "redirect:/home/viewCart";
	}
	
	// REMOVE SAVE FOR LATER ITEMS...!
	@GetMapping("/home/removeSaveLaterItem/{index}")
	public String removeSaveLaterItem(@PathVariable(value = "index") int index, RedirectAttributes redirectAttributes)
	{
		UniversalData_2.saveForLater.remove(index);
		
//		FLASH MESSAGE WITH TOASTR MODE !!
		redirectAttributes.addFlashAttribute("message", "If item removed from save for later");
		
		return "redirect:/home/viewCart";
	}
	
	// BACK TO CART...!
	@GetMapping("/home/moveToCart/{index}")
	public String moveToCart(@PathVariable(value = "index") int index, RedirectAttributes redirectAttributes)
	{
		Product remove = UniversalData_2.saveForLater.remove(index);
		UniversalData.cart.add(remove);
		
		
//		FLASH MESSAGE WITH TOASTR MODE !!
		redirectAttributes.addFlashAttribute("message", "If item moved back to cart");
		
		return "redirect:/home/viewCart";
	}
	
	// CART ITEMS CHECK OUT DETAILS...!
	@GetMapping("/checkDetails")		// AUTH.
	public String getCheckDetails(Model model)
	{
		List<Product> CheckOutDetails = UniversalData.cart;
		model.addAttribute("CheckOutDetails", CheckOutDetails);
		
		long count = CheckOutDetails.stream().count();
		model.addAttribute("count", count);
		
		// This will be recently taken for utilization as delivery consideration !!
		double Delivery_Charges= 0;
		double Delivery_Amount= 40;
		double Delivery_Band= 499;
		double sum = UniversalData.cart.stream().mapToDouble(Product::getDiscountedPrice).sum();
		if(sum>=1 && sum<=Delivery_Band)
		{
			Delivery_Charges= Delivery_Charges + Delivery_Amount;
			model.addAttribute("Delivery_Charges", Delivery_Charges);
			model.addAttribute("totalDicountedPrice", UniversalData.cart.stream().mapToDouble(Product::getDiscountedPrice).sum());
			model.addAttribute("totalDicountedPriceWithShipping", UniversalData.cart.stream().mapToDouble(Product::getDiscountedPrice).sum() + Delivery_Charges);
		}
		else
		{
			Delivery_Charges= Delivery_Charges+0.0;
			model.addAttribute("Delivery_Charges", Delivery_Charges);
			model.addAttribute("totalDicountedPrice", UniversalData.cart.stream().mapToDouble(Product::getDiscountedPrice).sum());
			model.addAttribute("totalDicountedPriceWithShipping", UniversalData.cart.stream().mapToDouble(Product::getDiscountedPrice).sum());
		}
		
		return "check-out";
	}
	
	// REMOVE CART CHECK OUT ITEMS AT CHECK OUT PAGE...!
	@GetMapping("/removeCartCheckOutItem/{index}")		// AUTH.
	public String removeCartCheckOutItem(@PathVariable(value = "index") int index)
	{
		if(index>0)
		{
			UniversalData.cart.remove(index);
		}
		return "redirect:/checkDetails";	
	}
	
	// ADD ITEMS AT CART CHECK OUT PAGE...!
	@GetMapping("/addToCartCheckOutPage/{id}")		// AUTH.
	public String addToCartCheckOutPage(@PathVariable(value = "id") Long id)
	{
		UniversalData.cart.add(productService.getOrEditProductById(id));
		return "redirect:/checkDetails";
	}
	
	// CART ITEMS DELIVERY ADDRESS PAGE...!
	@GetMapping("/deliveryAddressPage")		// AUTH.
	public String paymentPage(Model model, Principal principal, @ModelAttribute("message") String Flashmessage)
	{
		// GETTING USER ADDRESS HERE..!
		String email = principal.getName();
		User user = userService.checkEmail(email);
		model.addAttribute("user", user);
		Long id = user.getId();
		
//		 List of address on the basis of who currently login that moment, that means user id, By the way it return only authenticated user address !!
		List<Address> listOfAddress = addressService.findAllAddressByuserId(id);
		model.addAttribute("listOfAddress", listOfAddress);
		
		// CUSTOMER ORDER OBJECT FOR OPENING FORM PAGE..!
		CustomerOrderDTO customerOrderDTO= new CustomerOrderDTO();
		model.addAttribute("customerOrderDTO", customerOrderDTO);
		
		// CUSTOMER ADDRESS OBJECT FOR OPENING FORM PAGE..!
		AddressDTO addressDTO = new AddressDTO();
		model.addAttribute("addressDTO", addressDTO);
		
		// FLASH MESSAGE WITH TOASTR MODE !!
		model.addAttribute("message", Flashmessage);
				
		//MAKE HERE METHODE CALL...!
		getCheckDetails(model);
		
		return "cart-delivery-address-page";
	}
	
//===================================================BUY-NOW ITEMS DETAILS================================================================================//
	
	// ADD BUY-NOW CHECK OUT ITEMS...!
	@GetMapping("/getBuyNow/{id}")		// AUTH.
	public String getBuyNow(@PathVariable(value = "id") Long id, Model model)
	{	
		Product ProductById = productService.getOrEditProductById(id);
		Long id2 = ProductById.getId();
		
		if(id!=id2)
		{
			UniversalData_3.buyNow.clear();
		}
		
		UniversalData_3.buyNow.add(ProductById);
		
		/*
		// PAY TODAY ... ATTENTION HERE MR. RAJANIKANT...Using for loop!!
		List<Product> CheckOutDetails = UniversalData_3.buyNow; 
		for(Product ch : CheckOutDetails) 
		{
			Long id2 = ch.getId();
			
			// Its not add another items...!
			if(id!=id2)
			{
				UniversalData_3.buyNow.remove(ch);
				
			}
		}
		*/
		return "redirect:/buyNowCheckOut";
	}
	
	// REMOVE BUY-NOW CHECK OUT ITEMS...!
	@GetMapping("/removeBuynowCheckOutItem/{index}")		// AUTH.
	public String removeBuynowCheckOutItem(@PathVariable(value = "index") int index)
	{
		if(index>0 || index==0)
		{
			UniversalData_3.buyNow.remove(index);
		}
		return "redirect:/buyNowCheckOut";	
	}
	
	// This URI have just call above 2 times as redirect ...we make this @GetMapping("/home/buyNowCheckOut") to avoid refresh condition...because this add multiple items in buy-now-check-out page...!
	// This URI have been not used in any front-end page...just taken to redirecting above see...!
	// BUY-NOW ITEMS CHECK OUT DETAILS...!
	@GetMapping("/buyNowCheckOut")		// AUTH.
	public String getBuyNowDeatils(Model model)
	{	
		List<Product> CheckOutDetails = UniversalData_3.buyNow;		
		model.addAttribute("CheckOutDetails", CheckOutDetails);
		
		// COUNT THE ORDER SUMMARY AT CHECK-OUT PAGE...!
		long count = CheckOutDetails.stream().count();
		model.addAttribute("count", count);
		
		// This will be recently taken for utilization as buy now consideration !!
		double Delivery_Charges= 0;
		double Delivery_Amount= 40;
		double Delivery_Band= 499;
		double sum = UniversalData_3.buyNow.stream().mapToDouble(Product::getDiscountedPrice).sum();
		if(sum>=1 && sum<=Delivery_Band)
		{
			Delivery_Charges= Delivery_Charges + Delivery_Amount;
			model.addAttribute("Delivery_Charges", Delivery_Charges);
			model.addAttribute("totalDicountedPrice", UniversalData_3.buyNow.stream().mapToDouble(Product::getDiscountedPrice).sum());
			model.addAttribute("totalDicountedPriceWithShipping", UniversalData_3.buyNow.stream().mapToDouble(Product::getDiscountedPrice).sum() + Delivery_Charges);
		}
		else
		{
			Delivery_Charges= Delivery_Charges+0.0;
			model.addAttribute("Delivery_Charges", Delivery_Charges);
			model.addAttribute("totalDicountedPrice",UniversalData_3.buyNow.stream().mapToDouble(Product::getDiscountedPrice).sum());
			model.addAttribute("totalDicountedPriceWithShipping", UniversalData_3.buyNow.stream().mapToDouble(Product::getDiscountedPrice).sum());
		}
		
		return "buynow-check-out";
	}
	
	// BUY-NOW ITEMS DELIVERY ADDRESS PAGE...!
	@GetMapping("/buyNowDeliveryAddressPage")		// AUTH.
	public String buyNowpaymentPage(Model model, Principal principal, @ModelAttribute("message") String Flashmessage)
	{	
		List<Product> CheckOutDetails = UniversalData_3.buyNow;
		model.addAttribute("CheckOutDetails", CheckOutDetails);
		
		// COUNT THE ORDER SUMMARY AT DELEVERY ADDRESS PAGE...!
		long count = CheckOutDetails.stream().count();
		model.addAttribute("count", count);
		
		// This will be recently taken for utilization as buy now consideration !!
		double Delivery_Charges= 0;
		double Delivery_Amount= 40;
		double Delivery_Band= 499;
		double sum = UniversalData_3.buyNow.stream().mapToDouble(Product::getDiscountedPrice).sum();
		if(sum>=1 && sum<=Delivery_Band)
		{
			Delivery_Charges= Delivery_Charges + Delivery_Amount;
			model.addAttribute("Delivery_Charges", Delivery_Charges);
			model.addAttribute("totalDicountedPrice", UniversalData_3.buyNow.stream().mapToDouble(Product::getDiscountedPrice).sum());
			model.addAttribute("totalDicountedPriceWithShipping", UniversalData_3.buyNow.stream().mapToDouble(Product::getDiscountedPrice).sum() + Delivery_Charges);
		}
		else
		{
			Delivery_Charges= Delivery_Charges+0.0;
			model.addAttribute("Delivery_Charges", Delivery_Charges);
			model.addAttribute("totalDicountedPrice",UniversalData_3.buyNow.stream().mapToDouble(Product::getDiscountedPrice).sum());
			model.addAttribute("totalDicountedPriceWithShipping", UniversalData_3.buyNow.stream().mapToDouble(Product::getDiscountedPrice).sum());
		}
		
		// GETTING USER ADDRESS HERE..!
		String email = principal.getName();
		User user = userService.checkEmail(email);
		model.addAttribute("user", user);
		Long id = user.getId();
		
// 		List of address on the basis of who currently login that moment, that means user id, By the way it return only authenticated user address !!
		List<Address> listOfAddress = addressService.findAllAddressByuserId(id);
		model.addAttribute("listOfAddress", listOfAddress);
		
		// CUSTOMER ORDER OBJECT FOR OPENING FORM PAGE..!
		CustomerOrderDTO customerOrderDTO= new CustomerOrderDTO();
		model.addAttribute("customerOrderDTO", customerOrderDTO);
		
		// CUSTOMER ADDRESS OBJECT FOR OPENING FORM PAGE..!
		AddressDTO addressDTO = new AddressDTO();
		model.addAttribute("addressDTO", addressDTO);
		
		// FLASH MESSAGE WITH TOASTR MODE !!
		model.addAttribute("message", Flashmessage);
		
		return "buynow-delivery-address-page";
	}
}
