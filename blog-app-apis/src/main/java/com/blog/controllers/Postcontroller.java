package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.payloads.apiresponse;
import com.blog.services.fileservice;
import com.blog.services.impl.PostServiceImpl;

@RestController
@RequestMapping("/api/")
public class Postcontroller {

	@Autowired
	private PostServiceImpl impl;
	
	
	@Autowired
	private fileservice fileservice;
	
	
	 @Value("${project.image:images/}")
	    private String path;

	
	@PostMapping("/user/{userid}/category/{categoryid}/posts")
	public ResponseEntity<PostDto> createpost(@RequestBody PostDto dto,@PathVariable int userid , @PathVariable int categoryid){
		
		    PostDto dto2=  this.impl.createpost(dto, userid, categoryid);
		    
		    return new ResponseEntity<PostDto>(dto2,HttpStatus.CREATED);
		 
		
	}
	@GetMapping("/category/{categoryid}/posts")
	public ResponseEntity<List<PostDto>>  getbycategory(@PathVariable() int categoryid){
		
		List<PostDto>dtos=     this.impl.getPostByCategory(categoryid);
		
		return new ResponseEntity<List<PostDto>>(dtos,HttpStatus.OK);
	}
	
	@GetMapping("/user/{user}/posts")
	public ResponseEntity<List<PostDto>>  getbyuser(@PathVariable() int user){
		
		List<PostDto>dtos=     this.impl.getPostByUser(user);
		
		return new ResponseEntity<List<PostDto>>(dtos,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getallpost(
			@RequestParam(name = "pagenumber",defaultValue = "0",required = false) int pagenumber,
			@RequestParam(name = "pagesize",defaultValue = "5",required = false ) int pagesize,
			@RequestParam(name = "sortby", defaultValue = "id" ,required =  false) String sortby,
			@RequestParam(name="dirtby",defaultValue = "asc" ,required = false) String dirtby
			){
		
		PostResponse getallpost = this.impl.getallpost(pagenumber,pagesize,sortby,dirtby);
	return  new ResponseEntity<PostResponse>(getallpost,HttpStatus.OK);
		
	}
	@GetMapping("/posts/{postid}")
	public ResponseEntity<PostDto> getpostid(@PathVariable() int postid){
		
	PostDto dtos=  this.impl.getpostbyid(postid);
	return  new ResponseEntity<PostDto>(dtos,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/posts/{deleteid}")
	public ResponseEntity<apiresponse> deletepostbyid(@PathVariable("deleteid") int deleteid ){
		
		this.impl.deletepost(deleteid);
		
		
		return new ResponseEntity<apiresponse>(new apiresponse("post delete sussfully ",true),HttpStatus.OK);
		
	}
	
	@PutMapping("/posts/{updateid}")
	public ResponseEntity<PostDto> updatepost(@RequestBody PostDto dto, @PathVariable("updateid") int updateid){
		
	    PostDto dto2=	this.impl.updatepost(dto, updateid);
		
	    return new ResponseEntity<PostDto>(dto2,HttpStatus.OK);
	}
	
	
//	@GetMapping("/post/search/{title}")
//	public ResponseEntity<List<PostDto>> searchpost(@PathVariable("title") String title){
//		
//		List<PostDto> searchpost = this.impl.searchpost(title);
//		
//		
//		return new ResponseEntity<List<PostDto>>(searchpost,HttpStatus.OK);
//		
//	}
	@GetMapping("/post/search")
	public ResponseEntity<List<PostDto>> searchpost(
			@RequestParam(name = "title",defaultValue = "0",required = false) String title
){
		
		List<PostDto> searchpost = this.impl.searchpost(title);
		
		
		return new ResponseEntity<List<PostDto>>(searchpost,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	@PostMapping("/post/image/upload/{postid}")
	public ResponseEntity<?> uploadpostimage(@RequestParam("image") MultipartFile image){
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
}
