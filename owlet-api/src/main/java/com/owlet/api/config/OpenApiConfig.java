package com.owlet.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Owlet REST API",
                version = "1.0.0",
                description = """
                        Backend REST API for Owlet Educational Platform.
                        
                        This API provides services for:
                        - School Management
                        - Student Management
                        - Teacher Management
                        - Learning Sessions
                        - Assessment
                        - Authentication & Authorization
                        """,
                contact = @Contact(
                        name = "Owlet Team",
                        email = "support@owlet.com"
                ),
                license = @License(
                        name = "Private License"
                )
        ),
        security = {
                @SecurityRequirement(name = "Bearer Authentication")
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        description = "JWT Authorization header using the Bearer scheme.",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()

                .servers(List.of(
                        new Server()
                                .url("http://localhost:8081")
                                .description("Local Development")
                ))

                .externalDocs(
                        new ExternalDocumentation()
                                .description("Owlet Documentation")
                                .url("https://docs.owlet.com")
                );

    }

}

