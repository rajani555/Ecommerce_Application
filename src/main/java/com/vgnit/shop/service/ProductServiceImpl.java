package com.vgnit.shop.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vgnit.shop.dto.ProductDTO;
import com.vgnit.shop.entity.Product;
import com.vgnit.shop.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService 
{
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public Product addProduct(ProductDTO productDTO) 
	{
		// TODO Auto-generated method stub
		Product product= new Product();
		
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());		             // Let it market price     = 1000									
		product.setDescription(productDTO.getDescription());
		
		// Set product without any check condition!
		//product.setStock(productDTO.getStock());
		
		// Set product with the check condition!
		if (!productDTO.getStock().isBlank()) {
			product.setStock(productDTO.getStock());
		} else {
			product.setStock("0");
		}
		
		product.setColor(productDTO.getColor());
		product.setImageName(productDTO.getImageName());
		product.setImageName2(productDTO.getImageName2());
		product.setImageName3(productDTO.getImageName3());
		product.setImageName4(productDTO.getImageName4());
		product.setCategory(categoryService.getOrEditCategoryById(productDTO.getCategoryId()));
		product.setDiscountPersent(productDTO.getDiscountPersent());  // Let it discount rate  = 25 %	
		
		Double price = productDTO.getPrice();
		Double discountPersent = productDTO.getDiscountPersent();
		Double discRate = 100-discountPersent;
		
		Double amountAfterDisc = (discRate*price)/100;              // Let it price after disc = 750
		product.setDiscountedPrice(amountAfterDisc);											
		
		Double saveAmount = price - amountAfterDisc;				// Save Amount = market price - price after disc
		product.setSaveAmount(saveAmount);
																									
		product.setBrand(productDTO.getBrand());
		
		return productRepository.save(product);
		
	}

	@Override
	public List<Product> listofProduct(String keyword)
	{
		// TODO Auto-generated method stub
		if(keyword!=null)
		{
			return productRepository.search(keyword);
		}
		else
		{
			return productRepository.findAll();
		}
	}
	
	@Override
	public Page<Product> findPaginated(int pageNo, int pageSize) 
	{
		// TODO Auto-generated method stub
		Pageable pageable= PageRequest.of(pageNo - 1, pageSize);
		Page<Product> findAllPage = productRepository.findAll(pageable);
		return findAllPage;
	}
	
	@Override
	public void deleteProduct(Long id)
	{
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
	}

	@Override
	public Product getOrEditProductById(Long id) 
	{
		// TODO Auto-generated method stub
		Optional<Product> findById = productRepository.findById(id);
		Product product = findById.get();
		return product;
	}

	@Override
	public List<Product> listofProductByCategorySearch(Long id) 
	{
		// TODO Auto-generated method stub
		List<Product> AllProductBycategoryId = productRepository.findAllBycategoryId(id);
		return AllProductBycategoryId;
	}

	@Override
	public Long countTheProduct() 
	{
		// TODO Auto-generated method stub
		long countProduct = productRepository.count();
		return countProduct;
	}

}
