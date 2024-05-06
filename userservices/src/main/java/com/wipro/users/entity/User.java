package com.wipro.users.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long userId;
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must contain only letters and numbers")
	private String username;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one letter, one special character, and be at least 8 characters long")
	
	private String password;

}
