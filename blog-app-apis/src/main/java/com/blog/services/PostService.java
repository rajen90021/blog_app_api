package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

public interface PostService {

	
	PostDto  createpost(PostDto dto ,int userid,int categoryid);
	
	List<PostDto> getPostByCategory(int categoryid);
	
	
	List<PostDto> getPostByUser(int userid);
	
	
	PostResponse  getallpost(int pagenumber ,int pagesize ,String sortby,String dirtby);
	
	
	PostDto  getpostbyid(int id);
	  public void   deletepost(int deleteid);
	  
	  
	  PostDto   updatepost( PostDto dto, int updateid);
	  
	  List<PostDto> searchpost(String keyword);
}
