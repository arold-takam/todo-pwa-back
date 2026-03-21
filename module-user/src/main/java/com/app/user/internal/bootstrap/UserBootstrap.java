package com.app.user.internal.bootstrap;

import com.app.user.api.dto.UserRequestDto;
import com.app.user.internal.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class UserBootstrap {
	private static final Logger log = LoggerFactory.getLogger(UserBootstrap.class);
	private final UserService userService;
	
//	-------------------------------------------------------------------------------------
@PostConstruct
public void initDefaultUser() {
	log.info("=== UserBootstrap démarré ==="); // ← LOG TRÈS VISIBLE pour confirmer exécution
	String defaultUsername = "defaultuser";
	
	try {
		userService.findByUsername(defaultUsername);
		log.info("Utilisateur par défaut '{}' existe déjà – skip", defaultUsername);
	} catch (Exception e) {
		log.info("Création de l'utilisateur par défaut '{}'", defaultUsername);
		
		UserRequestDto dto = new UserRequestDto(
			defaultUsername,
			"default@example.com",
			"default123"
		);
		
		userService.save(dto);
		log.info("Utilisateur par défaut créé avec succès (ID=1)");
	}
}
}
