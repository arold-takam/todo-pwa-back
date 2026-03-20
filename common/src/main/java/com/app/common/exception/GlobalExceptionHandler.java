package com.app.common.exception;

import com.app.common.shared.exception.BusinessException;
import com.app.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException ex){
		return  new ResponseEntity<>(
			ApiResponse.error(ex.getMessage()),
			ex.getStatus()
		);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex){
		return ResponseEntity.internalServerError().body(
			ApiResponse.error("Internal error occurred.")
		);
	}
}
