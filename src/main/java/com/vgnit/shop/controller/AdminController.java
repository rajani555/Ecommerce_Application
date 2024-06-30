package com.vgnit.shop.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.vgnit.shop.dto.ProductDTO;
import com.vgnit.shop.entity.Category;
import com.vgnit.shop.entity.Product;
import com.vgnit.shop.entity.User;
import com.vgnit.shop.repository.UserRepository;
import com.vgnit.shop.service.CategoryService;
import com.vgnit.shop.service.ProductService;

@Controller
public class AdminController 
{
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserRepository userRepository;
	
	// Category Section...!!
	
	@GetMapping("/admin/adminHome")
	public String adminHome(Model model)
	{
		// Total Products Count!
		Long countTheProduct = productService.countTheProduct();
		model.addAttribute("countTheProduct", countTheProduct);
		
		// Total Categories Count!
		Long countTheCategory = categoryService.countTheCategory();
		model.addAttribute("countTheCategory", countTheCategory);
		return "admin-dashboard";
	}
	
	@ModelAttribute
	public void getLoggedUser(Model model, Principal principal)
	{
		if (principal != null)
		{
			String email = principal.getName();
			User user = userRepository.findByEmail(email);
			model.addAttribute("user", user);
		}
	}
	
	@GetMapping("/admin/categoryList")
	public String getAdminCategory()
	{
		return "category-list";
	}
	
	@GetMapping("/admin/getCategoryPage")
	public String getCategoryPage(Model model)
	{
		Category category = new Category();
		model.addAttribute("category", category);
		return "add-category";
	}
	
	@PostMapping("/admin/addCategory")
	public String addCategory(@ModelAttribute("category") @RequestBody Category category)
	{
		categoryService.addCategory(category);
		return"redirect:/admin/listofCategory";
	}
	
	/*
	// This is search features is not applicable on Pagination .
	@GetMapping("/admin/listofCategory")
	public String listofCategory(Model model, @Param("keyword") String keyword)
	{
		List<Category> listofCategory = categoryService.listofCategory(keyword);
		model.addAttribute("listofCategory", listofCategory);
		return "category-list";
	}
	*/

	
	// This is search features is not applicable on Pagination .
	@GetMapping("/admin/listofCategory")
	public String listofCategory(Model model, @Param("keyword") String keyword)
	{
		if(keyword!= null)
		{
			List<Category> listofCategory = categoryService.listofCategory(keyword);
			model.addAttribute("listofCategory", listofCategory);
			return "category-list";
		}
		else
		{
			return findCategroyPaginated(1, model);
		}
		
	}

	@GetMapping("/admin/Catpage/{pageNum}")
	public String findCategroyPaginated(@PathVariable(value = "pageNum") int pageNo, Model model)
	{
		int pageSize= 5;
		Page<Category> page = categoryService.findPaginated(pageNo, pageSize);
		List<Category> listofCategory = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listofCategory", listofCategory);
		return "category-list";
	}
	
	@GetMapping("/admin/deleteCategory/{id}")
	public String deleteCategory(@PathVariable(value = "id") Long id)
	{
		categoryService.deleteCategory(id);
		return"redirect:/admin/listofCategory";
	}
	
	@GetMapping("/admin/getOrEditCategory/{id}")
	public String getOrEditCategoryById(@PathVariable(value = "id") Long id, Model model)
	{
		Category getorEditCategoryById = categoryService.getOrEditCategoryById(id);
		model.addAttribute("category", getorEditCategoryById);
		return "edit-category";
	}
	
	@PostMapping("/admin/updateCategory")
	public String updateCategory(@ModelAttribute("category") Category category)
	{
		categoryService.addCategory(category);
		return"redirect:/admin/listofCategory";
	}
	
	// Product Section...!!
	
	@GetMapping("/admin/getProductPage")
	public String getProductPage(Model model)
	{
		ProductDTO productDTO= new ProductDTO();
		model.addAttribute("productDTO", productDTO);
		model.addAttribute("listofCategoryWithOutKeyw", categoryService.listofCategory());
		return "add-product";
	}
	
	// #Image Variable uploadDir
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productimages";
	@PostMapping("/admin/addProduct")
	public String addProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
							 @RequestParam("productImage") MultipartFile file,
							 @RequestParam("productImage2") MultipartFile file2,
							 @RequestParam("productImage3") MultipartFile file3,
							 @RequestParam("productImage4") MultipartFile file4,
							 @RequestParam("imgName") String imgName,
							 @RequestParam("imgName2") String imgName2,
							 @RequestParam("imgName3") String imgName3,
							 @RequestParam("imgName4") String imgName4) throws IOException
	{
		String imageUUID;
		if(!file.isEmpty())
		{
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());	
		}
		else
		{
			imageUUID = imgName;
		}
		// Add here...!!
		productDTO.setImageName(imageUUID);
		
		String imageUUID2;
		if(!file2.isEmpty())
		{
			imageUUID2 = file2.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID2);
			Files.write(fileNameAndPath, file2.getBytes());	
		}
		else
		{
			imageUUID2 = imgName2;
		}
		// Add here...!!
		productDTO.setImageName2(imageUUID2);
		
		String imageUUID3;
		if(!file3.isEmpty())
		{
			imageUUID3 = file3.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID3);
			Files.write(fileNameAndPath, file3.getBytes());	
		}
		else
		{
			imageUUID3 = imgName3;
		}
		// Add here...!!
		productDTO.setImageName3(imageUUID3);
		
		String imageUUID4;
		if(!file4.isEmpty())
		{
			imageUUID4 = file4.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID4);
			Files.write(fileNameAndPath, file4.getBytes());	
		}
		else
		{
			imageUUID4 = imgName4;
		}
		// Add here...!!
		productDTO.setImageName4(imageUUID4);
		
		productService.addProduct(productDTO);
		return "redirect:/admin/listofProduct";
	}
	
	/*
	// This is search features is not applicable on Pagination .
	@GetMapping("/admin/listofProduct")
	public String listofProduct(Model model, @Param("keyword") String keyword)
	{
		List<Product> listofProduct = productService.listofProduct(keyword);
		model.addAttribute("listofProduct", listofProduct);
		return "product-list";
	}
	*/
	
	
	// This search features is applicable on pagination.
	@GetMapping("/admin/listofProduct")
	public String listofProduct(Model model, @Param("keyword") String keyword)
	{
		if(keyword!=null)
		{
			List<Product> listofProduct = productService.listofProduct(keyword);
			model.addAttribute("listofProduct", listofProduct);
			return "product-list";
		}
		else
		{
			return findProductPaginated(1, model);
		}
	}
	
	@GetMapping("/admin/page/{pageNo}")
	public String findProductPaginated(@PathVariable(value = "pageNo") int pageNo, Model model)
	{
		int pageSize= 8;
		Page<Product> page = productService.findPaginated(pageNo, pageSize);
		List<Product> listofProduct = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listofProduct", listofProduct);
		return "product-list";
	}
	
	@GetMapping("/admin/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(value = "id") Long id)
	{
		productService.deleteProduct(id);
		return "redirect:/admin/listofProduct";
	}
	
	@GetMapping("/admin/getOrEditProduct/{id}")
	public String getOrEditProduct(@PathVariable(value = "id") Long id, Model model)
	{
		Product oldproduct = productService.getOrEditProductById(id);
		
		ProductDTO productDTO= new ProductDTO();
		
		productDTO.setId(oldproduct.getId());
		productDTO.setName(oldproduct.getName());
		productDTO.setPrice(oldproduct.getPrice());
		productDTO.setDescription(oldproduct.getDescription());
		productDTO.setStock(oldproduct.getStock());
		productDTO.setColor(oldproduct.getColor());
		productDTO.setImageName(oldproduct.getImageName());
		productDTO.setImageName2(oldproduct.getImageName2());
		productDTO.setImageName3(oldproduct.getImageName3());
		productDTO.setImageName4(oldproduct.getImageName4());
		productDTO.setCategoryId(oldproduct.getCategory().getId());
		productDTO.setDiscountedPrice(oldproduct.getDiscountedPrice());
		productDTO.setDiscountPersent(oldproduct.getDiscountPersent());
		productDTO.setSaveAmount(oldproduct.getSaveAmount());
		productDTO.setBrand(oldproduct.getBrand());
		
		model.addAttribute("productDTO", productDTO);
		model.addAttribute("listofCategoryWithOutKeyw", categoryService.listofCategory());
		
		return "edit-product";
	}
	
	// This is currently not in use...!
	@PostMapping("/admin/updateProduct")
	public String updateProduct(@ModelAttribute("product") ProductDTO productDTO)
	{
		productService.addProduct(productDTO);
		return"redirect:/admin/listofProduct";
	}
	
	
}
