package com.app.task.internal.service;

import com.app.task.api.dto.TaskRequestDto;
import com.app.task.api.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
	
	TaskResponseDto save(TaskRequestDto dto);
	
	List<TaskResponseDto> findAll();
	
	List<TaskResponseDto> findAllByUserId(Long id);
	
	TaskResponseDto findById(Long id);
	
	TaskResponseDto update(Long id, TaskRequestDto dto);
	
	TaskResponseDto validate(Long id);
	
	boolean delete(Long id);
}
