package com.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOriginPatterns(
				// Développement local
				"http://localhost:5173",
				"http://localhost:4173",
				"http://localhost:3000",
				// Netlify — accepte toutes les previews et le domaine prod
				"https://*.netlify.app",
				"https://mytodopwa.netlify.app",
				// Render — si le front est aussi sur Render
				"https://*.onrender.com"
			)
			.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
			.allowedHeaders("*")
			.allowCredentials(true)
			.maxAge(3600);
	}
}