package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserRoleDTO {
	private int userID; 
	private String name;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String createdBy;
	private String updatedBy;
	private int roleID;
	private String roleName;
	private LocalDateTime rowVersion;
	
}
