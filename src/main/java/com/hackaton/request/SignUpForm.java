package com.hackaton.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignUpForm {
	 @Size(min = 3, max = 50)
	    private String firstName;
	    
	    @NotBlank
	    @Size(min = 3, max = 50)
	    private String lastName;


	    @NotBlank
	    @Size(min = 3, max = 50)
	    private String username;
	    
	    @NotBlank
	    @Size(min = 3, max = 50)
	    private String dob;

	    @NotBlank
	    @Size(max = 60)
	    @Email(message="Please provide valid Email Address")
	    private String email;
	    
	    @NotBlank
	    @Size(max = 10)
	    private String phone;
	        
	    private String gender;
		private String religion;
		private String mother_tongue;
		private String country;
		private String state;
		private String city;
	    
	    
	    
	    @NotBlank
	    private Set<String> level;
	    
	    @NotBlank
	    @Size(min = 6, max = 40)
	    private String password;

}
