package com.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	
	

	private int categoryId;
	
	@NotBlank
	@Size(min=5)
	  private String category_title; // Change the field name to match the column name

	
	@NotBlank
	@Size(min = 10)
	    private String category_discription;
}
