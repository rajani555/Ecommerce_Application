package com.vgnit.shop.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import com.vgnit.shop.entity.Product;
import com.vgnit.shop.entity.Review;
import com.vgnit.shop.entity.User;
import com.vgnit.shop.repository.UserRepository;
import com.vgnit.shop.service.CategoryService;
import com.vgnit.shop.service.ProductService;
import com.vgnit.shop.service.ReviewService;
import ForAll.UniversalData;

@Controller
public class HomeController
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewService reviewService;
	
	@ModelAttribute
	public void getLoggedUser(Model model, Principal principal)
	{
		if (principal != null)
		{
			String email = principal.getName();
			User user = userRepository.findByEmail(email);
			
			String role = user.getRole().toUpperCase();
			if(role.equalsIgnoreCase("ROLE_ADMIN"))
			{
				model.addAttribute("admin", role);
				model.addAttribute("user", user);
			}
			else
			{
				model.addAttribute("user", user);
			}
		}
	}
	
	@GetMapping("/home/for-all-type-product-filter")
	public String ForAllTypeProductFilter(Model model, @Param("keyword") String keyword)
	{
		// List of all categories!
		model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
		
		// List of all products!
		model.addAttribute("listofProduct", productService.listofProduct(keyword));
		
		// Cart view!
		model.addAttribute("cartCountValue", UniversalData.cart.size());   //Attention Here !!
		return "home-for-all-type-product-filter";
	}
	
	@GetMapping({"/", "/home"})
	public String home(Model model, @Param("keyword") String keyword)
	{	
//		long randomPositiveLong = Math.abs(UUID.randomUUID().getMostSignificantBits());
//		System.out.println(randomPositiveLong);
		
		// List of all categories!
		model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
		
		// List of all products for ANIMATION SLIDE!
		model.addAttribute("listofAllProduct", productService.listofProduct(keyword));

		// Using this list... multiple time in below code...please see!
		List<Product> listofProduct = productService.listofProduct(keyword);
		
		// Grouping by category!
		// This code limit to show only 1 items out of all product from list!
		List<Product> Mobile = listofProduct.stream().filter(b -> b.getCategory().getName().equalsIgnoreCase("Mobile")).limit(1).collect(Collectors.toList());
		model.addAttribute("listofMobile", Mobile);
		
		// This code does not put any limit to show product from list means it fetch all!
//		List<Product> Mobile = listofProduct.stream().filter(b -> b.getCategory().getName().equalsIgnoreCase("Mobile")).collect(Collectors.toList());
//		model.addAttribute("listofMobile", Mobile);

		
		// Grouping by category!
		// This code limit to show only 1 items out of all product from list!
		List<Product> Electronics = listofProduct.stream().filter(b -> b.getCategory().getName().equalsIgnoreCase("Electronics")).limit(1).collect(Collectors.toList());
		model.addAttribute("listofElectronics", Electronics);
		
		// This code does not put any limit to show product from list means it fetch all!
//		List<Product> Electronics = listofProduct.stream().filter(b -> b.getCategory().getName().equalsIgnoreCase("Electronics")).collect(Collectors.toList());
//		model.addAttribute("listofProduct", Electronics);
		
		
		// Cosmetics!
		List<Product> Cosmetics = listofProduct.stream().filter(c -> c.getCategory().getName().equalsIgnoreCase("Cosmetics")).limit(1).collect(Collectors.toList());
		model.addAttribute("listofCosmetics", Cosmetics);
		
		// Kids_Section!
		List<Product> Kids_Section = listofProduct.stream().filter(k -> k.getCategory().getName().equalsIgnoreCase("Kids Section")).limit(1).collect(Collectors.toList());
		model.addAttribute("listofKids_Section", Kids_Section);
		
		// Grouping by category!
		// This code limit to show only 1 items out of all product from list!
		List<Product> Lighting = listofProduct.stream().filter(b -> b.getCategory().getName().equalsIgnoreCase("Lighting")).limit(1).collect(Collectors.toList());
		model.addAttribute("listofLighting", Lighting);
				
		// This code does not put any limit to show product from list means it fetch all!
//		List<Product> Lighting = listofProduct.stream().filter(b -> b.getCategory().getName().equalsIgnoreCase("Lighting")).collect(Collectors.toList());
//		model.addAttribute("listofLighting", Lighting);
		
		// Toys!
		List<Product> Toys = listofProduct.stream().filter(t -> t.getCategory().getName().equalsIgnoreCase("Toys")).limit(1).collect(Collectors.toList());
		model.addAttribute("listofToys", Toys);
		
		// Fashion!
		List<Product> Fashion = listofProduct.stream().filter(f -> f.getCategory().getName().equalsIgnoreCase("Fashion")).limit(1).collect(Collectors.toList());
		model.addAttribute("listofFashion", Fashion);
		
		// Shoes!
		List<Product> Shoes = listofProduct.stream().filter(s -> s.getCategory().getName().equalsIgnoreCase("shoes")).limit(1).collect(Collectors.toList());
		model.addAttribute("listofShoes", Shoes);
		
		// Bags!
		List<Product> Laptopbags = listofProduct.stream().filter(lb -> lb.getCategory().getName().equalsIgnoreCase("Bags")).limit(1).collect(Collectors.toList());
		model.addAttribute("listofLaptopbags", Laptopbags);
		
		// Books!
		List<Product> Books = listofProduct.stream().filter(bk -> bk.getCategory().getName().equalsIgnoreCase("Books")).limit(1).collect(Collectors.toList());
		model.addAttribute("listofBooks", Books);
		
		// Trolley Bag!
		List<Product> Trolley_Bags = listofProduct.stream().filter(bk -> bk.getCategory().getName().equalsIgnoreCase("Trolley Bags")).limit(1).collect(Collectors.toList());
		model.addAttribute("listofTrolley_Bags", Trolley_Bags);
		
		// Trolley Bag!
		List<Product> Wireless_Earphones = listofProduct.stream().filter(bk -> bk.getCategory().getName().equalsIgnoreCase("Wireless Earphones")).limit(1).collect(Collectors.toList());
		model.addAttribute("Wireless_Earphones", Wireless_Earphones);
		
		// Watches!
		List<Product> Watches = listofProduct.stream().filter(bk -> bk.getCategory().getName().equalsIgnoreCase("Watches")).limit(1).collect(Collectors.toList());
		model.addAttribute("Watches", Watches);
		
		// Cart view!
		model.addAttribute("cartCountValue", UniversalData.cart.size());   //Attention Here !!
		return "home1";
	}
	
	@GetMapping("/home/getProductByCategorySearch/{id}")
	public String getProductByCategorySearch(@PathVariable(value = "id") Long id, Model model)
	{
		model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
		List<Product> listofProductByCategorySearch = productService.listofProductByCategorySearch(id);  //Attention Here !!
		model.addAttribute("listofProductByCategorySearch", listofProductByCategorySearch);
		
		// limit to one for slide banner!
		List<Product> collect = listofProductByCategorySearch.stream().limit(1).collect(Collectors.toList());
		model.addAttribute("categoryFound", collect);
		
		model.addAttribute("cartCountValue", UniversalData.cart.size());   //Attention Here !!
		return "category-search";
	}
	
	@GetMapping("/home/getDetailOfParticularProduct/{id}")
	public String getDetailOfParticularProduct(@PathVariable(value = "id") Long id, Model model)
	{
		Product getorEditProduct = productService.getOrEditProductById(id);
		model.addAttribute("getorEditProduct", getorEditProduct);
		
		String stockGet = getorEditProduct.getStock();
		if(stockGet.equalsIgnoreCase("0"))
		{
			String out_of_stock= "Out of stock";
			model.addAttribute("OutOfStock", out_of_stock);
		}
		else
		{
			String in_stock= "In stock";
			model.addAttribute("InStock", in_stock);
		}
		
		// This is send list of categories on view at the NAVBAR section!
		model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
		
		// Cart value Attention Here !!
		model.addAttribute("cartCountValue", UniversalData.cart.size());   
		
		// Attention Here !!
		// Getting all list of review by particular product id !!
		// Own custom method !!
		List<Review> allreviewById = reviewService.findAllReviewByproductId(id);
		model.addAttribute("allreviewById", allreviewById);
		
		// Getting size for list of review by particular product id !!
		int totalReview = allreviewById.size();
		model.addAttribute("totalReview", totalReview);
		
	
		// Fetching user name from the above list (i.e. reviewById)  by using loop !! Because this list (i.e. reviewById) providing all details of Review entity !!
//		for(int i=0; i<allreviewById.size(); i++)
//		{
//			Review review = allreviewById.get(i);
//			String fullname = review.getUser().getFullname();
//			model.addAttribute("fullname", fullname);
//		}
		
		return "detail";
	}
	
	
	// FILTER PRICE SECTION...!!
	
	@GetMapping("/home/FilterPriceUnder500")
	public String searchProductUnder500(String keyword, Model model) 
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(product -> product.getDiscountedPrice() <=500).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterPrice500To1000")
	public String searchProduct500To1000(String keyword, Model model) 
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(product -> product.getDiscountedPrice() >=500 && product.getDiscountedPrice() <=1000).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterPrice1000To5000")
	public String searchProduct1000To5000(String keyword, Model model) 
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(product -> product.getDiscountedPrice() >=1000 && product.getDiscountedPrice() <=5000).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterPrice5000To10000")
	public String searchProduct5000To10000(String keyword, Model model) 
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(product -> product.getDiscountedPrice() >=5000 && product.getDiscountedPrice() <=10000).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterPrice10000To20000")
	public String searchProduct10000To20000(String keyword, Model model) 
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(product -> product.getDiscountedPrice() >=10000 && product.getDiscountedPrice() <=20000).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterPrice20000To50000")
	public String searchProduct20000To50000(String keyword, Model model) 
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(product -> product.getDiscountedPrice() >=20000 && product.getDiscountedPrice() <=50000).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterPrice50000To100000")
	public String searchProduct50000To100000(String keyword, Model model) 
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(product -> product.getDiscountedPrice() >=50000 && product.getDiscountedPrice() <=100000).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterPriceOver100000")
	public String searchProductOver100000(String keyword, Model model) 
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(product -> product.getDiscountedPrice() >=100000).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	
	// FILTER BRAND SECTION...!!
	
	@GetMapping("/home/FilterByRealme")
	public String serchProductByBrandR(String keyword, Model model)
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(b -> b.getBrand().equalsIgnoreCase("realme")).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	
	@GetMapping("/home/FilterByToofan")
	public String serchProductByBrandT(String keyword, Model model)
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(b -> b.getBrand().equalsIgnoreCase("Toofan")).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterByPuma")
	public String serchProductByBrandP(String keyword, Model model)
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(b -> b.getBrand().equalsIgnoreCase("Puma")).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterByLg")
	public String serchProductByBrandL(String keyword, Model model)
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(b -> b.getBrand().equalsIgnoreCase("LG")).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterByPhilips")
	public String serchProductByBrandPH(String keyword, Model model)
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(b -> b.getBrand().equalsIgnoreCase("Philips")).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterByLakme")
	public String serchProductByBrandLK(String keyword, Model model)
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(b -> b.getBrand().equalsIgnoreCase("Lakme")).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterByBajaj")
	public String serchProductByBrandBJ(String keyword, Model model)
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(b -> b.getBrand().equalsIgnoreCase("Bajaj")).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterBySkybag")
	public String serchProductByBrandS(String keyword, Model model)
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(b -> b.getBrand().equalsIgnoreCase("skybag")).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterByEveready")
	public String serchProductByBrandE(String keyword, Model model)
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(b -> b.getBrand().equalsIgnoreCase("Eveready")).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	@GetMapping("/home/FilterByAction")
	public String serchProductByBrandA(String keyword, Model model)
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		List<Product> collect = listofProduct.stream().filter(b -> b.getBrand().equalsIgnoreCase("Action")).collect(Collectors.toList());
		if(!collect.isEmpty())
		{
			model.addAttribute("listofProduct", collect);
			model.addAttribute("listofCategoryWithOutKey", categoryService.listofCategory());
			return "home-for-all-type-product-filter";
		}
		else
		{
			return "nothing";
		}
	}
	
	
	
}
