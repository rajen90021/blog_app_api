package com.blog.payloads;

import org.hibernate.validator.constraints.NotEmpty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private int id;
	
	
	
	
	
	@NotEmpty(message = "write proper name ")
	@Size(min = 4 ,message = "username must be more then 4 character !!")
	private String name;

	@Email(message = "email is not valid !!")
	@NotEmpty(message = "email is incorrect")
	private String email;
	
	@NotEmpty(message = " not be empty password")
	 @Size(min = 3,max = 10,message = "password must be min 3 and maximum is 10 !!")
	private String password;
 
	
	@NotEmpty(message = "about not be empty write something !!")
	private String about ;

}
