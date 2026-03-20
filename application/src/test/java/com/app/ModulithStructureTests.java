package com.app;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModulithStructureTests {
	
	@Test
	void verifyModules() {
		ApplicationModules modules = ApplicationModules.of(TodoApplication.class);
		modules.verify(); // Vérifie la cohérence des frontières
	}
	
	@Test
	void createModuleDocumentation() {
		ApplicationModules modules = ApplicationModules.of(TodoApplication.class);
		new Documenter(modules).writeDocumentation();
		// génère la doc (PlantUML, HTML, etc.) dans target/spring-modulith-docs
	}
}
