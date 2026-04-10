package com.app.user.shared;

public interface UserSharedService {
	
	boolean existById(Long userId);
	
	boolean existsByUsername(String username);
	
}