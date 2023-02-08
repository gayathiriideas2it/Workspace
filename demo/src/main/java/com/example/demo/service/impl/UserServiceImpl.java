package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRoleDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.CustomModelMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceInterface;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.fge.jsonpatch.JsonPatch;

@Service
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	private CustomModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserRoleDTO> getAllUsers() {
		return modelMapper.mapList(userRepository.findAll(), UserRoleDTO.class);
	}

	@Override
	public Optional<UserRoleDTO> getUser(int userID) {
		Optional<User> user = userRepository.findById(userID);
		return Optional.ofNullable(modelMapper.map(user.get(), UserRoleDTO.class));
	}

	@Override
	public UserRoleDTO addUser(UserRoleDTO usersRoleDTO) {
		User user = modelMapper.map(usersRoleDTO, User.class);
		return modelMapper.map(userRepository.save(user), UserRoleDTO.class);

	}

	@Override
	public Optional<UserRoleDTO> updateUser(int userID, UserRoleDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		Optional<User> userEntity = userRepository.findById(userID);
		user.setCreated(userEntity.get().getCreated());
		if (!userEntity.isEmpty()) {
			return Optional.ofNullable(modelMapper.map(userRepository.save(user), UserRoleDTO.class));
		} else
			return Optional.empty();
	}

	@Override
	public Optional<UserRoleDTO> partialUpdateUser(int userID, JsonPatch patch) {
		System.out.println(userID);
		Optional<User> user = userRepository.findById(userID);
		User user1 = applyPatchToCustomer(patch, user.get());
		if (user.isPresent()) {
			return Optional.ofNullable(modelMapper.map(userRepository.save(user1), UserRoleDTO.class));
		}
		else
			return Optional.empty();
	}

	@Override
	public void deleteUser(int userID) {
		userRepository.deleteById(userID);
	}

	@Override
	public Page<UserRoleDTO> getUser(int page, int size) {
		return new PageImpl<UserRoleDTO>(modelMapper
				.mapList(userRepository.findAll(PageRequest.of(page, size)).getContent(), UserRoleDTO.class));
	}

	private User applyPatchToCustomer(JsonPatch patch, User user) {
		try {
			ObjectMapper om = JsonMapper.builder().findAndAddModules().build();
			JsonNode patched = patch.apply(om.convertValue(user, JsonNode.class));
			User updated = om.treeToValue(patched, User.class);
			return updated;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
