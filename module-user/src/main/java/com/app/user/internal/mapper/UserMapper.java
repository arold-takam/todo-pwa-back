package com.app.user.internal.mapper;

import com.app.user.api.dto.UserRequestDto;
import com.app.user.api.dto.UserResponseDto;
import com.app.user.internal.domain.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserEntity toEntity(UserRequestDto dto);
	
	UserResponseDto toResponse(UserEntity entity);
	
	List<UserResponseDto> toResponseList(List<UserEntity> entities);
}
