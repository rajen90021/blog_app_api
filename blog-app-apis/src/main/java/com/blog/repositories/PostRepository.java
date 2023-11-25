package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostRepository  extends JpaRepository<Post, Integer> {
	
	
	List<Post> findByUser(User user);
	
    @Query("SELECT p FROM Post p WHERE p.category = :category")
    List<Post> findPostsByCategory(@Param("category") Category category);
    
    
//    List<Post> findByTitleContaining(String keyword);
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")
    List<Post> findByTitleContaining(@Param("keyword") String keyword);

 }
