package com.example.HR_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hrManagementOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("HR Management API")
                        .description("API documentation for authentication and user management modules")
                        .version("v1")
                        .contact(new Contact()
                                .name("HR Management Team")
                                .email("support@hrmanagement.local"))
                        .license(new License()
                                .name("Internal Use")
                                .url("https://example.com/internal")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Paste the JWT access token returned from the login or register API")));
    }
}
