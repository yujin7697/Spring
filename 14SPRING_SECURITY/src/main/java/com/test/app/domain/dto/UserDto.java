package com.test.app.domain.dto;

import lombok.Data;

@Data
public class UserDto {
	private String username;
	private String password;
	private String email;
	private String role;
}