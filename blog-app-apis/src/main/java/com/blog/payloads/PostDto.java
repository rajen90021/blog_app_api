package com.blog.payloads;

import java.util.Date;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	
	 private int id;
	
	private String title;
	
	 private String  content;
	 
 private String imagename;
	 
	 
	 private Date addeddate;
	 
	
	 private CategoryDto category;
	 
	
	 private UserDto user;
	 
	 
}
