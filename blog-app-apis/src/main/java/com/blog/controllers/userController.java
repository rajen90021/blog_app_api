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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.UserDto;
import com.blog.payloads.apiresponse;
import com.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class userController {

	
	@Autowired
	 private UserService service;
	
	@PostMapping("/")
	private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto ){
		
		
	 UserDto createduser=	this.service.createUser(dto);
	 
	 return new ResponseEntity<>(createduser,HttpStatus.CREATED);
	}
	
	//update
	
	@PutMapping("/update/{userid}")
	private ResponseEntity<UserDto> updateuser(@Valid @RequestBody UserDto dto,@PathVariable("userid") int id ){
		
		  UserDto dto2= this.service.updateUser(dto, id);
		
		  return new ResponseEntity<UserDto>(dto2,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/delete/{userid}")
	private ResponseEntity<apiresponse> deleteuser(@PathVariable("userid") int id){
		
		this.service.deleteUser(id);
		return new ResponseEntity<apiresponse>(new apiresponse("user delete sussfully", true),HttpStatus.OK);
		
	}
	//get all user
	@GetMapping("/getalluser")
	private ResponseEntity<List<UserDto>> getalluser(){
		
		List<UserDto> dtos=  this.service.getAllUsers();
		return new ResponseEntity<List<UserDto>>(dtos, HttpStatus.OK);

	
	}
	
	//get by id
	@GetMapping("/getbyid/{userid}")
	private ResponseEntity<UserDto> getbyid(@PathVariable("userid") int userid){
		
	 UserDto dto=	this.service.getUserById(userid);
	 
	 return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
	}
	
	
}
