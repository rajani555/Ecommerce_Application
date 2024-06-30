package com.vgnit.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.vgnit.shop.dto.ProductDTO;
import com.vgnit.shop.entity.Product;

public interface ProductService 
{
	public Product addProduct(ProductDTO productDTO);
	
	public List<Product> listofProduct(String keyword);
	
	public Page<Product> findPaginated(int pageNo, int pageSize);
	
	public void deleteProduct(Long id);
	
	public Product getOrEditProductById(Long id);
	
	public List<Product> listofProductByCategorySearch(Long id);
	
	public Long countTheProduct();

}
