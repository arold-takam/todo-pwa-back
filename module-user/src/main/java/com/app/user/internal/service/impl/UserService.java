package com.app.user.internal.service.impl;

import com.app.common.EntityNotFoundException;
import com.app.user.api.dto.UserRequestDto;
import com.app.user.api.dto.UserResponseDto;
import com.app.user.internal.domain.UserEntity;
import com.app.user.internal.mapper.UserMapper;
import com.app.user.internal.repository.UserRepository;
import com.app.user.shared.UserCreatedEvent;
import com.app.user.shared.UserDeletedEvent;
import com.app.user.shared.UserSharedService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements com.app.user.internal.service.UserService, UserSharedService {
	private final UserRepository repository;
	private final UserMapper userMapper;
	private final ApplicationEventPublisher eventPublisher;
	
//	--------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override @Transactional
	public UserResponseDto save(UserRequestDto requestDto) {
		if (requestDto.username().isBlank() || requestDto.email().isBlank() || requestDto.password().isBlank()){
			throw new IllegalArgumentException("Wrong data submit, try again.");
		}
		
		UserEntity savedEntity = repository.save(userMapper.toEntity(requestDto));
		
		eventPublisher.publishEvent(new UserCreatedEvent(savedEntity.getId()));
		
		return userMapper.toResponse(savedEntity);
	}
	
	@Override
	public List<UserResponseDto> findAll() {
		List<UserResponseDto>responseDtoList = new ArrayList<>();
		
		List<UserEntity>userEntityList = repository.findAll();
		for (UserEntity entity: userEntityList){
			responseDtoList.add(userMapper.toResponse(entity));
		}
		
		return responseDtoList;
	}
	
	@Override
	public UserResponseDto findById(Long id) {
		UserEntity entity = repository.findById(id).orElseThrow(()->new EntityNotFoundException("No userFound with the id: "+id));
		
		return userMapper.toResponse(entity);
	}
	
	@Override
	public UserResponseDto findByUsername(String username) {
		UserEntity entity = repository.findByUsername(username);
		if (entity == null){
			throw new EntityNotFoundException("No user found with the username: " + username);
		}
		
		return userMapper.toResponse(entity);
	}
	
	@Override
	public UserResponseDto update(Long id, UserRequestDto requestDto) {
		UserEntity entity = repository.findById(id).orElseThrow(()->new EntityNotFoundException("No userFound with the id: "+id));
		
		entity.setUsername(requestDto.username());
		entity.setEmail(requestDto.email());
		entity.setPassword(requestDto.password());
		
		return userMapper.toResponse(repository.save(entity));
	}
	
	@Override @Transactional
	public boolean delete(Long id) {
		UserEntity entity = repository.findById(id).orElseThrow(()->new EntityNotFoundException("No userFound with the id: "+id));
		
		repository.delete(entity);
		
		eventPublisher.publishEvent(new UserDeletedEvent(id));
		
		return true;
	}
	
	@Override
	public boolean existById(Long userID) {
		return repository.existsById(userID);
	}
}
