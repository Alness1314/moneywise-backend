package com.alness.moneywise.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    private static final String SCHEME_NAME = "JWT Auth";
	private static final String SCHEME = "Bearer";

	@Bean
	public OpenAPI customOpenAPI() {
		var openApi = new OpenAPI()
				.info(getInfo());
		addSecurity(openApi);
		return openApi;
	}

	private Info getInfo() {
		return new Info()
				.title("Moneywise")
				.description("Application to manage income, expenses and generate monthly financial reports, available on mobile and web.")
				.version("1.0")
				.license(getLicense());
	}

	private License getLicense() {
		return new License()
				.name("Alness Zadro")
				.url("https://github.com/Alness1314");
	}
    
	private void addSecurity(OpenAPI openApi) {
		var components = createComponents();
		var securityItem = new SecurityRequirement().addList(SCHEME_NAME);
		openApi.components(components).addSecurityItem(securityItem);
	}

	private Components createComponents() {
		var components = new Components();
		components.addSecuritySchemes(SCHEME_NAME, createSecurityScheme());
		return components;
	}

	private SecurityScheme createSecurityScheme() {
		return new SecurityScheme()
				.name(SCHEME_NAME)
				.type(SecurityScheme.Type.HTTP)
				.scheme(SCHEME);
	}
}
