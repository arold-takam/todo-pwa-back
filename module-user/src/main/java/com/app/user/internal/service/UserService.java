package com.app.user.internal.service;

import com.app.user.api.dto.UserRequestDto;
import com.app.user.api.dto.UserResponseDto;

import java.util.List;

public interface UserService {

	UserResponseDto save(UserRequestDto requestDto);
	
	List<UserResponseDto> findAll();
	
	UserResponseDto findById(Long id);
	
	UserResponseDto findByUsername(String username);
	
	UserResponseDto update(Long id, UserRequestDto requestDto);
	
	boolean delete(Long id);

}
