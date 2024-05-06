package com.wipro.users.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Admin extends User {

	private String contactNum;
	@NotEmpty
	@Email
	private String email;

}
