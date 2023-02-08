package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleServiceInterface;


@Service
public class RoleServiceImpl implements RoleServiceInterface {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role addRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> getRole(int roleID) {
		return roleRepository.findById(roleID);
	}

	@Override
	public Optional<Role> updateRole(Role role, int roleID) {
		Optional<Role> roleEntity = roleRepository.findById(roleID);
		if (!roleEntity.isEmpty()) {
			return Optional.ofNullable(roleRepository.save(role));
		} else
			return Optional.empty();
	}

	@Override
	public Optional<Role> partialUpdateRole(Role role, int roleID) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteRole(int roleID) {
		roleRepository.deleteById(roleID);

	}

}
