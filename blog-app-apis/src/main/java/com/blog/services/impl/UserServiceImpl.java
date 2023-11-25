package com.blog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.exceptions.*;
import com.blog.entities.User;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;

import jakarta.validation.Valid;



@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepository repository;
	
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDto createUser(@Valid UserDto userdto) {
		// TODO Auto-generated method stub
		  User user= dtoToUser(userdto);
		  User savedUser=     repository.save(user);
		return this.userToDto(savedUser);
	}

	
	
	///update 
	@Override
	public UserDto updateUser(@Valid UserDto userdto,int id) {
	
		
		 Optional<User> optionalUser = repository.findById(id);

		    if (optionalUser.isPresent()) {
		        User userToUpdate = optionalUser.get();
		        userToUpdate.setId(userdto.getId());
		        userToUpdate.setName(userdto.getName());
		        userToUpdate.setEmail(userdto.getEmail());
		        userToUpdate.setPassword(userdto.getPassword());
		        userToUpdate.setAbout(userdto.getAbout());

		        User updatedUser = repository.save(userToUpdate);
		        return userToDto(updatedUser);
		    } else {
		        throw new ResourceNotFoundException("User", "id", id);
		    }
		///*******************************************************************************
//	User user=	this.repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
//	user.setId(userdto.getId());
//	user.setName(userdto.getName());
//	user.setEmail(userdto.getEmail());
//	user.setPassword(userdto.getPassword());
//	user.setAbout(userdto.getAbout());
//	 User userupdated	=this.repository.save(user);
//	    UserDto userdto1= this.userToDto(userupdated);
//	 		
//		return userdto1;
	}

	@Override
	public UserDto getUserById(int id) {
		
	Optional<User> optionaluser=	this.repository.findById(id);
		
	if(optionaluser.isPresent()) {
		  User user= optionaluser.get();
		  return userToDto(user);
	}else {
		throw new ResourceNotFoundException("User", "id", id);
	}
	     
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		  List<User> allUsers = repository.findAll();
		    List<UserDto> userDtos = userToDto(allUsers);
		    return userDtos;
	}
	
	private List<UserDto> userToDto(List<User> users) {
	    List<UserDto> userDtos = new ArrayList<>();
	    for (User user : users) {
	        userDtos.add(userToDto(user));
	    }
	    return userDtos;
	}

	@Override
	public void deleteUser(int id) {
	
		Optional<User> optionaluser=  this.repository.findById(id);
		
		if(optionaluser.isPresent()) {
			User user= optionaluser.get();
			this.repository.delete(user);
		}else {
			throw new ResourceNotFoundException("user ", "id ", id);
		}
		
		
	}
	
	private User dtoToUser(UserDto userDto) {
		
		User user = this.mapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
	
		return user;
	
		
	}
private UserDto userToDto(User user) {
		
	UserDto userdto = this.mapper.map(user, UserDto.class);
	
	//new UserDto();
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setPassword(user.getPassword());
//		userdto.setAbout(user.getAbout());
	
		return userdto;
	
		
	}


}
