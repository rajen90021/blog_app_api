package com.blog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.payloads.UserDto;
import com.blog.repositories.CategoryRepository;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceimp  implements CategoryService{

	@Autowired
	 private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	
	///create 
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
	     
	Category category=	this.mapper.map(categoryDto, Category.class);
		    
	    this.categoryRepository.save(category);
	       CategoryDto categoryDto2=  this.mapper.map(category, CategoryDto.class);
		
		return categoryDto2;
	}

	
	
	//update 
	@Override
	public CategoryDto updatecategory(CategoryDto categoryDto, int id) {
		
		
	Optional<Category> optional=	this.categoryRepository.findById(id);
     
	     if(optional.isPresent()) {
	    	 Category category= optional.get();
	    	 category.setCategory_title(categoryDto.getCategory_title());
	    	 category.setCategory_discription(categoryDto.getCategory_discription());
	    	 Category category2= this.categoryRepository.save(category);
	    	 
	    	 CategoryDto categoryDto2=	 this.mapper.map(category2, CategoryDto.class);
	    	 return categoryDto2;
	     }else {
	    	 
		        throw new ResourceNotFoundException("cateogry", "Category id", id);
	     }
	

	}

	
	///delete 
	@Override
	public void deletecategory(int id) {
	
	Optional<Category> optional=	this.categoryRepository.findById(id);
		
	if(optional.isPresent()) {
		  Category category=  optional.get();
		  this.categoryRepository.delete(category);
	}
	else {
		throw new ResourceNotFoundException("Category", " id ", id);
	}
	}

	
	
	
	///get single category 
	@Override
	public CategoryDto singlecategory(int id) {
	
		Optional<Category> optional= this.categoryRepository.findById(id);
		if(optional.isPresent()) {
			  Category category=  optional.get();
			CategoryDto categoryDto=  this.mapper.map(category, CategoryDto.class);
			
			return categoryDto;
		}else {
			throw new ResourceNotFoundException("User", "id", id);
		}
		

	}

	//get all category 
	@Override
	public List<CategoryDto> allcategory() {
	
		
		
		   List<Category> categories = categoryRepository.findAll();
	        List<CategoryDto> categoryDtos = categories.stream()
	                .map(category -> mapper.map(category, CategoryDto.class))
	                .collect(Collectors.toList());
	        return categoryDtos;
		
	 
	
	
	}
	
}
