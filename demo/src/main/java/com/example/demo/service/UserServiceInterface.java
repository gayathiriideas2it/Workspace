package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.demo.dto.UserRoleDTO;
import com.github.fge.jsonpatch.JsonPatch;

public interface UserServiceInterface {
	
	public List<UserRoleDTO> getAllUsers();
	public UserRoleDTO addUser(UserRoleDTO usersRoleDTO);
	public Optional<UserRoleDTO> getUser(int userID);
	public Optional<UserRoleDTO> updateUser(int userID, UserRoleDTO userDTO);
	public Optional<UserRoleDTO> partialUpdateUser(int userID, JsonPatch patch);
	public void deleteUser(int userID);
	public Page<UserRoleDTO> getUser(int page, int size);

}
