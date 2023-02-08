package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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
import com.example.demo.entity.Role;
import com.example.demo.service.RoleServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {

	private RoleServiceInterface roleService;

	@GetMapping
	public ResponceDTO getAllRoles() {
		List<Role> list = roleService.getAllRoles();
		if (list.isEmpty())
			return new ResponceDTO(HttpStatus.NO_CONTENT.value(), "Roles Not Found", list);
		else
			return new ResponceDTO(HttpStatus.FOUND.value(), "Roles Found", list);
	}

	@GetMapping("/{id}")
	public ResponceDTO getRole(@PathVariable("id") Integer roleID) {
		Optional<Role> role = roleService.getRole(roleID);
		if (!role.isPresent())
			return new ResponceDTO(HttpStatus.NO_CONTENT.value(), "Role not Found", role.get());
		else
			return new ResponceDTO(HttpStatus.FOUND.value(), "Role Found", role.get());
	}

	@PostMapping
	public ResponceDTO addRole(@Valid @RequestBody Role role){
		return new ResponceDTO(HttpStatus.CREATED.value(), "Role Created", roleService.addRole(role));
	}

	@PutMapping("/{id}")
	public ResponceDTO updateRole(@RequestBody Role role, @PathVariable("id") int roleID) {
		Optional<Role> role1 = roleService.updateRole(role, roleID);
		if (role1.isEmpty())
			return new ResponceDTO(HttpStatus.NO_CONTENT.value(), "Role Not Found", role1.get());
		else
			return new ResponceDTO(HttpStatus.OK.value(), "Role Updated", role1.get());
	}

	@PatchMapping("/role/{id}")
	public void updateRole(@RequestBody Role role, @PathVariable("id") Integer roleID) {
		roleService.partialUpdateRole(role, roleID);
	}

	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable("id") Integer roleID) {
		roleService.deleteRole(roleID);
	}

	public RoleController(RoleServiceInterface roleService) {
		this.roleService = roleService;
	}
}
