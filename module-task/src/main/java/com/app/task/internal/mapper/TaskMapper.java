package com.app.task.internal.mapper;

import com.app.task.api.dto.TaskRequestDto;
import com.app.task.api.dto.TaskResponseDto;
import com.app.task.internal.domain.TaskEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

	TaskEntity toEntity(TaskRequestDto dto);
	
	TaskResponseDto toResponse(TaskEntity entity);
	
	List<TaskResponseDto>toListResponseDto(List<TaskEntity> entityList);
}
