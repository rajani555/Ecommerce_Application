package com.vgnit.shop.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vgnit.shop.entity.Category;
import com.vgnit.shop.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) 
	{
		// TODO Auto-generated method stub
		Category save = categoryRepository.save(category);
		return save;
	}

	@Override
	public List<Category> listofCategory(String keyword) 
	{
		// TODO Auto-generated method stub
		if(keyword!=null)
		{
			return categoryRepository.serach(keyword);
		}
		else
		{
			return categoryRepository.findAll();
		}	
	}
	
	// See Attention !!
	@Override
	public List<Category> listofCategory() 
	{
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}
	
	@Override
	public Page<Category> findPaginated(int pageNo, int pageSize) 
	{
		// TODO Auto-generated method stub
		Pageable pageable= PageRequest.of(pageNo - 1, pageSize);
		Page<Category> findAllPage = categoryRepository.findAll(pageable);
		return findAllPage;
	}

	@Override
	public void deleteCategory(Long id) 
	{
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
	}

	@Override
	public Category getOrEditCategoryById(Long id) 
	{
		// TODO Auto-generated method stub
		Optional<Category> findById = categoryRepository.findById(id);
		Category category = findById.get();
		return category;
	}
	
	@Override
	public Long countTheCategory() 
	{
		// TODO Auto-generated method stub
		long countCategory = categoryRepository.count();
		return countCategory;
	}


}
