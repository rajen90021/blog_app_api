package com.blog.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
public class Post{

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	
	
	 @Column(length = 1000, nullable = false)
	 private String title;
	 
	 
	 @Column(length = 1000, nullable = false)
	 private String  content;
	 
	 private String imagename;
	 
	 
	 private Date addeddate;
	 
	 @ManyToOne
	 @JoinColumn(name = "category_id")
	 private Category category;
	 
	 @ManyToOne
	 private User user;
	 
}
