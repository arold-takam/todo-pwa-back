package com.app.task.api.controller;

import com.app.common.ApiResponse;
import com.app.task.api.dto.TaskRequestDto;
import com.app.task.api.dto.TaskResponseDto;
import com.app.task.internal.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task") // accessible via /api/task
@RequiredArgsConstructor
public class TaskController {
	
	private final TaskService taskService;
	
	@PostMapping(path = "/save")
	public ResponseEntity<ApiResponse<TaskResponseDto>> save(@RequestBody TaskRequestDto dto) {
		var response = taskService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success(response));
	}
	
	@GetMapping(path = "/find/all")
	public ResponseEntity<ApiResponse<List<TaskResponseDto>>> findAll() {
		return ResponseEntity.ok(ApiResponse.success(taskService.findAll()));
	}
	
	@GetMapping(path = "/find/all/{userId}")
	public ResponseEntity<ApiResponse<List<TaskResponseDto>>> findAllByUserId(@PathVariable Long userId){
		return ResponseEntity.ok(ApiResponse.success(taskService.findAllByUserId(userId)));
	}
	
	@GetMapping(path = "/find/byId/{id}")
	public ResponseEntity<ApiResponse<TaskResponseDto>> findById(@PathVariable Long id) {
		return ResponseEntity.ok(ApiResponse.success(taskService.findById(id)));
	}
	
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<ApiResponse<TaskResponseDto>> update(@PathVariable Long id, @RequestBody TaskRequestDto dto) {
		return ResponseEntity.ok(ApiResponse.success(taskService.update(id, dto)));
	}
	
	@PatchMapping(path = "/validate/{id}")
	public ResponseEntity<ApiResponse<TaskResponseDto>> validate(@PathVariable Long id) {
		return ResponseEntity.ok(ApiResponse.success(taskService.validate(id)));
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
		taskService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success(null));
	}
}
