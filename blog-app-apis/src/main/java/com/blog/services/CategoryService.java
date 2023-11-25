package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService {

	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	
	//update
	public  CategoryDto updatecategory(CategoryDto categoryDto,int id);
	
	////delete
	
	public void deletecategory(int id);
	
	//get single category
	
	public CategoryDto singlecategory(int id);
	
	//get all caregory
	public List<CategoryDto> allcategory();
	
}
