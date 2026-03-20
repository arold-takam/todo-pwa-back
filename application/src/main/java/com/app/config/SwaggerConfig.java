package com.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Todo List Modular API")
				.version("1.0.0")
				.description("Architecture Hybride : Monolithe Modulaire avec Spring Modulith")
				.contact(new Contact().name("Équipe de développement")));
	}
}