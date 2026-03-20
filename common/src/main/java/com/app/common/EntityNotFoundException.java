package com.app.common;

import com.app.common.shared.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BusinessException {
	public EntityNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
