package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponceDTO;
import com.example.demo.dto.UserRoleDTO;
import com.example.demo.service.UserServiceInterface;
import com.github.fge.jsonpatch.JsonPatch;

@RestController
@RefreshScope
@RequestMapping("/user")
public class UserController {

	private UserServiceInterface userService;
	
	@Value("${msg}")
	String message;

	@Value("")
	private String username;

	@Value("")
	public String password;

	@GetMapping
	public ResponceDTO getAllUsers() {
		
		List<UserRoleDTO> list = userService.getAllUsers();
		if (list.isEmpty())
			return new ResponceDTO(HttpStatus.NO_CONTENT.value(), "Users Not Found", list);

		else
			return new ResponceDTO(HttpStatus.FOUND.value(), "Users Found", list);

	}

	@GetMapping("/{id}")
	public ResponceDTO getUser(@PathVariable("id") int userID) {
		Optional<UserRoleDTO> userDTO = userService.getUser(userID);
		if (!userDTO.isPresent())
			return new ResponceDTO(HttpStatus.NO_CONTENT.value(), "User Not Found", userDTO.get());

		else
			return new ResponceDTO(HttpStatus.FOUND.value(), "User Found", userDTO.get());

	}

	@GetMapping("/{page}/{size}")
	public ResponceDTO getUser(@PathVariable("page") int page, @PathVariable("size") int size) {
		Page<UserRoleDTO> userDTO = userService.getUser(page, size);
		if (!userDTO.isEmpty())
			return new ResponceDTO(HttpStatus.NO_CONTENT.value(), "User Not Found", userDTO);
		else
			return new ResponceDTO(HttpStatus.FOUND.value(), "User Found", userDTO);
	}

	@PostMapping
	public ResponceDTO addUser(@RequestBody UserRoleDTO usersRoleDTO) {
		return new ResponceDTO(HttpStatus.CREATED.value(), "User Added", userService.addUser(usersRoleDTO));

	}

	@PutMapping("/{id}")
	public ResponceDTO updateUser(@RequestBody UserRoleDTO userRoleDTO, @PathVariable("id") int userID) {
		Optional<UserRoleDTO> userdto = userService.updateUser(userID, userRoleDTO);
		if (userdto.isPresent()) {
			return new ResponceDTO(HttpStatus.OK.value(), "User Updated", userdto);
		} else
			return new ResponceDTO(HttpStatus.NO_CONTENT.value(), "User Not Found", userdto);
	}

	@DeleteMapping("/{id}")
	public ResponceDTO deleteUser(@PathVariable("id") int userID) {
		userService.deleteUser(userID);
		return new ResponceDTO(HttpStatus.OK.value(), "User Deleted", new Object());
	}

	@PatchMapping(path="/{id}",consumes = "application/json-patch+json")
	public ResponceDTO partialUpdateUser(@RequestBody JsonPatch patch, @PathVariable("id") int userID) {
		Optional<UserRoleDTO> userdto = userService.partialUpdateUser(userID, patch);
		if (userdto.isPresent()) {
			return new ResponceDTO(HttpStatus.OK.value(), "User Updated", userdto);
		} else
			return new ResponceDTO(HttpStatus.NO_CONTENT.value(), "User Not Found", userdto);
	}

	public UserController(UserServiceInterface userService) {
		this.userService = userService;
		System.out.println(message);
	}

}
