package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Role;

public interface RoleServiceInterface {
	
	public Role addRole(Role role);
	public List<Role> getAllRoles();
	public Optional<Role> getRole(int roleID);
	public Optional<Role> updateRole(Role role, int roleID);
	public Optional<Role> partialUpdateRole(Role role, int roleID);
	public void deleteRole(int roleID);

}
