package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CategoryDto;
import com.blog.payloads.apiresponse;
import com.blog.services.impl.CategoryServiceimp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	
	@Autowired
	private CategoryServiceimp categoryServiceimp;
	
	///create 
	@PostMapping("/")	
	public  ResponseEntity<CategoryDto> createdcategory(@Valid @RequestBody CategoryDto categoryDto){
		
		
		  CategoryDto categoryDto2=  this.categoryServiceimp.createCategory(categoryDto);
	
	    return   new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.CREATED);
	
	}
	
	//update 
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody  CategoryDto categoryDto ,@PathVariable("id")  int id){
		
	CategoryDto categoryDto2=	this.categoryServiceimp.updatecategory(categoryDto, id);
		
	return new  ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.OK);
		
	}
	//delete
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<apiresponse> categorydelete(@PathVariable("id") int id){
		
		this.categoryServiceimp.deletecategory(id);
		
		return new ResponseEntity<apiresponse>(new apiresponse("delete succfully ",true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/singlecat/{id}")
	public ResponseEntity<CategoryDto> getcategory(@PathVariable("id") int id){
	 CategoryDto categoryDto2=	this.categoryServiceimp.singlecategory(id);
		
	 return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.OK);
		
	}
	
	
	//getallcategory
	
	@GetMapping("/getall")
	public ResponseEntity<List<CategoryDto>> allcategory(){
		
	 List<CategoryDto> categoryDtos=	this.categoryServiceimp.allcategory();
	  
	return new ResponseEntity<List<CategoryDto>>(categoryDtos,HttpStatus.OK);
	}
	
	
	
	
	
	
}
