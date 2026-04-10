package com.app.bootstrap;

import com.app.user.shared.UserSharedService;
import com.app.user.shared.UserBootstrapPort;
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
	
	// On injecte UNIQUEMENT les interfaces publiques du module user (package shared/)
	private final UserSharedService userSharedService;
	private final UserBootstrapPort userBootstrapPort;
	
	@PostConstruct
	public void initDefaultUser() {
		log.info("=== UserBootstrap démarré ===");
		String defaultUsername = "defaultuser";
		
		if (userSharedService.existsByUsername(defaultUsername)) {
			log.info("Utilisateur par défaut '{}' existe déjà – skip", defaultUsername);
		} else {
			log.info("Création de l'utilisateur par défaut '{}'", defaultUsername);
			userBootstrapPort.createDefaultUser(defaultUsername, "default@example.com", "default123");
			log.info("Utilisateur par défaut créé avec succès");
		}
	}
}