package com.app.user.shared;

public interface UserBootstrapPort {
	
	void createDefaultUser(String username, String email, String password);
	
}