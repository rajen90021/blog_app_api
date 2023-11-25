package com.blog.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;



@Service
public class PostServiceImpl  implements PostService {

	@Autowired
	 private PostRepository postRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userrepository;
	
	
	//create **********
	@Override
	public PostDto createpost(PostDto postdto, int userid, int categoryid) {
	
		
		  User user;
		    Category category;

		
		    Optional<User> optional=     this.userrepository.findById(userid);
		
		    if(optional.isPresent()) {
		    	
		      user=	  optional.get();
		    }
		    else {
		    	throw new ResourceNotFoundException("user", "userid", userid);
		    }
		    ///////////////////////////////////////
		    
		    Optional<Category> optional2=   this.categoryRepository.findById(categoryid);
		    if(optional2.isPresent()) {
		    	
			     category=	  optional2.get();
			    }
			    else {
			    	throw new ResourceNotFoundException("category", "category id ", categoryid);
			    }
		    ////////////////////////////////////////////
		    
		     Post post=    this.mapper.map(postdto, Post.class);
		     
		     post.setAddeddate(new Date());
		     post.setImagename("default.png");
		     post.setUser(user);
		     post.setCategory(category);
		  Post post2= this.postRepository.save(post);	
		     
		PostDto dto= this.mapper.map(post2,PostDto.class);
		return  dto;
	}


	@Override
	public List<PostDto> getPostByCategory(int categoryid) {
		 Category category;

	    Optional<Category> optional2=   this.categoryRepository.findById(categoryid);
	    if(optional2.isPresent()) {
	    	
		     category=	  optional2.get();
		    }
		    else {
		    	throw new ResourceNotFoundException("category", "category id ", categoryid);
		    }
	    
	    List<Post> listofpost =   this.postRepository.findPostsByCategory(category);
		
	    

	    List<PostDto> postDtos = new ArrayList<>(listofpost.size()); // Initialize the list with the expected size

	    for (int i = 0; i < listofpost.size(); i++) {
	        Post post = listofpost.get(i);
	        PostDto postDto = mapper.map(post, PostDto.class);
	        postDtos.add(postDto);
	    }

	    return postDtos;

	}


	@Override
	public List<PostDto> getPostByUser(int userid) {
		  User user;
		 Optional<User> optional=     this.userrepository.findById(userid);
			
		    if(optional.isPresent()) {
		    	
		      user=	  optional.get();
		    }
		    else {
		    	throw new ResourceNotFoundException("user", "userid", userid);
		    }
		    
		List<Post> list=    this.postRepository.findByUser(user);
		

	    List<PostDto> postDtos = new ArrayList<>(list.size()); // Initialize the list with the expected size

	    for (int i = 0; i < list.size(); i++) {
	        Post post = list.get(i);
	        PostDto postDto = mapper.map(post, PostDto.class);
	        postDtos.add(postDto);
	    }

	    return postDtos;
		
		
	}


	@Override
	public PostResponse getallpost(int pagenumber ,int pagesize,String sortby,String dirtby) {
		//int pagesize=5;
		//int pagenumber=1;
		
		  Sort  sortby2 = null;
		if(dirtby.equalsIgnoreCase("asc")) {
			sortby2= sortby2.by(sortby).ascending();
			
		}else {
			sortby2= sortby2.by(sortby).descending();
		}
		
		Pageable p= PageRequest.of(pagenumber, pagesize,sortby2);
		
	               Page<Post> pagepost = this.postRepository.findAll(p);
	               
	               		List<Post> listofpost = pagepost.getContent();
	
		   List<PostDto> dtos= new ArrayList<>(listofpost.size());
		   
		    
		   for(int i=0;i< listofpost.size();i++) {
			   
			 Post post=  listofpost.get(i);
			 
			PostDto postdto= this.mapper.map(post, PostDto.class);
			dtos.add(postdto);
			
		   }
		   
		   PostResponse postResponse= new PostResponse();
		   postResponse.setContent(dtos);
		   postResponse.setPagenumber(pagepost.getNumber());
		   postResponse.setPagesize(pagepost.getSize());
		   postResponse.setTotalelement(pagepost.getTotalElements());
		   postResponse.setTotalpage(pagepost.getTotalPages());
		   postResponse.setLastpage(pagepost.isLast());
	         
		return postResponse;
	}


	@Override
	public PostDto getpostbyid(int postid) {
		
		 Optional<Post> optional=     this.postRepository.findById(postid);
		if(optional.isPresent()) {
	      	Post post=	optional.get();
	 PostDto dto=     	this.mapper.map(post, PostDto.class);
	      	
	      	return  dto; 
		}else {
		throw new ResourceNotFoundException("postid", "post id", postid);
		}
	}


	@Override
	public void deletepost(int deleteid) {
	
	               Optional<Post> optional=  	this.postRepository.findById(deleteid);
		
	               
	               if(optional.isPresent()) {
	            	      Post post= optional.get();
	            	      this.postRepository.delete(post);
	               }else {
	            	   
	            		throw new ResourceNotFoundException("deleteid", " delete id ", deleteid);
	               }
	}


	@Override
	public PostDto updatepost(PostDto dto, int updateid) {
	
		        Optional<Post> optional=    this.postRepository.findById(updateid);
		if(optional.isPresent()) {
			 Post post= optional.get();
			 post.setContent(dto.getContent());
			 post.setTitle(dto.getTitle());
			 
			Post post2= this.postRepository.save(post);
		   PostDto dto2=	 this.mapper.map(post2, PostDto.class);
		   return dto2;
		}
		else {
			
			
			throw new ResourceNotFoundException("updateid", " update id ", updateid);
		}
		
		
	}


	@Override
	public List<PostDto> searchpost(String keyword) {
	
		List<Post> findByTitleContained = this.postRepository.findByTitleContaining(keyword);
		
		       List<PostDto> list= new ArrayList<>(findByTitleContained.size());
		       
		       for(int i=0;i< findByTitleContained.size();i++) {
		    	   
		    	       Post post = findByTitleContained.get(i);
		    	       PostDto map = this.mapper.map(post, PostDto.class);
		    	       list.add(map);
		       }
		       return list;
		
		
		
	}

}
