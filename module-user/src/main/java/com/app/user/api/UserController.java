package com.app.user.api;

import com.app.common.ApiResponse;
import com.app.user.api.dto.UserRequestDto;
import com.app.user.api.dto.UserResponseDto;
import com.app.user.internal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
public class UserController {
	private final UserService service;
	
//	--------------------------------------------------------------------------------------------------------------------------
	@PostMapping(path = "/save")
	public ResponseEntity<ApiResponse<UserResponseDto>> save(@RequestBody UserRequestDto requestDto){
		UserResponseDto response = service.save(requestDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
	}
	
	@GetMapping(path = "/find/all")
	public ResponseEntity<ApiResponse<List<UserResponseDto>>> findAll(){
		return ResponseEntity.ok(ApiResponse.success(service.findAll()));
	}
	
	@GetMapping(path = "/find/byId/{id}")
	public ResponseEntity<ApiResponse<UserResponseDto>> findById(@PathVariable Long id){
		return ResponseEntity.ok(ApiResponse.success(service.findById(id)));
	}
	
	@GetMapping(path = "/find/byUsername")
	public ResponseEntity<ApiResponse<UserResponseDto>> findByUsername(@RequestParam String username){
		return ResponseEntity.ok(ApiResponse.success(service.findByUsername(username)));
	}
	
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<ApiResponse<UserResponseDto>> update(@PathVariable Long id, @RequestBody UserRequestDto requestDto){
		return ResponseEntity.ok(ApiResponse.success(service.update(id, requestDto)));
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id){
		return ResponseEntity.ok(ApiResponse.success(service.delete(id)));
	}
	
}
