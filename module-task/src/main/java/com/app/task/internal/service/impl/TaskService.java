package com.app.task.internal.service.impl;

import com.app.common.EntityNotFoundException;
import com.app.user.shared.UserSharedService;
import com.app.task.api.dto.TaskRequestDto;
import com.app.task.api.dto.TaskResponseDto;
import com.app.task.internal.domain.TaskEntity;
import com.app.task.internal.mapper.TaskMapper;
import com.app.task.internal.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService implements com.app.task.internal.service.TaskService {
	private final TaskRepository repository;
	private final TaskMapper mapper;
	private final UserSharedService userSharedService;
	
	@Override
	@Transactional
	public TaskResponseDto save(TaskRequestDto dto) {
		if (dto.title() == null || dto.title().isBlank()) {
			throw new IllegalArgumentException("Title cannot be empty");
		}
		
		// --- LA VALIDATION PRAGMATIQUE ---
		if (!userSharedService.existById(dto.userId())) {
			throw new EntityNotFoundException("User not found with id: " + dto.userId());
		}
		// ---------------------------------
		
		TaskEntity entity = mapper.toEntity(dto);
		entity.setDone(false);
		entity.setUserId(dto.userId());
		
		return mapper.toResponse(repository.save(entity));
	}
	
	@Override
	public List<TaskResponseDto> findAll() {
		return repository.findAll().stream()
			.map(mapper::toResponse)
			.collect(Collectors.toList());
	}
	
	@Override
	public List<TaskResponseDto> findAllByUserId(Long id) {
		if (!userSharedService.existById(id)){
			throw new EntityNotFoundException("No user found with the id: "+ id);
		}
		
		var response =  repository.findAllByUserId(id);
		
		return mapper.toListResponseDto(response);
	}
	
	@Override
	public TaskResponseDto findById(Long id) {
		return repository.findById(id)
			.map(mapper::toResponse)
			.orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
	}
	
	@Override
	@Transactional
	public TaskResponseDto update(Long id, TaskRequestDto dto) {
		TaskEntity entity = repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Task not found"));
		entity.setTitle(dto.title());
		entity.setDetails(dto.details());
		entity.setDate(dto.date());
		entity.setTime(dto.time());
		entity.setDone(dto.isDone());
		return mapper.toResponse(repository.save(entity));
	}
	
	@Override
	@Transactional
	public TaskResponseDto validate(Long id) {
		TaskEntity entity = repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Task not found"));
		entity.setDone(true);
		return mapper.toResponse(repository.save(entity));
	}
	
	@Override
	@Transactional
	public boolean delete(Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("Task not found");
		}
		repository.deleteById(id);
		return true;
	}
}