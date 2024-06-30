package com.vgnit.shop.service;
import java.util.List;

import org.springframework.data.domain.Page;

import com.vgnit.shop.entity.Category;

public interface CategoryService
{
	public Category addCategory(Category category);
	
	public List<Category> listofCategory(String keyword);
	
	public Page<Category> findPaginated(int pageNo, int pageSize);
	
	public void deleteCategory(Long id);
	
	public Category getOrEditCategoryById(Long id);
	
	public List<Category> listofCategory();
	
	public Long countTheCategory();

	
}
