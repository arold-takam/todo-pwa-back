package com.app.user.internal.bootstrap;

import com.app.user.api.dto.UserRequestDto;
import com.app.user.internal.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class UserBootstrap {
	private final UserService userService;
	
//	-------------------------------------------------------------------------------------
	@PostConstruct
	public void initDefaultUser(){
		try {
			userService.findByUsername("toto");
		}catch (Exception e){
			UserRequestDto defaultUser = new UserRequestDto(
				"toto",
				"toto@",
				"1234"
			);
			userService.save(defaultUser);
			System.out.println("default user saved: defaultUser (ID = 1)");
		}
	}
}
