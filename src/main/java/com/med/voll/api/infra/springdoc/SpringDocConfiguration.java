package com.med.voll.api.infra.springdoc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Clinica-API-med-voll",
                description = "API Rest de la aplicación Voll.med...",
                version = "1.0.0",
                contact = @Contact(name = "Christian Ariel Garay", email = "christiangaray959@gmail.com")
        ),
        servers = {
                @Server(description = "DEV SERVER", url = "http://localhost:8000"),
                @Server(description = "PROD SERVER", url = "http://cris959:8080")
        },
        // El nombre aquí debe coincidir EXACTAMENTE con el de @SecurityScheme
        security = @SecurityRequirement(name = "bearer-key")
)
@SecurityScheme(
        name = "bearer-key",
        description = "JWT Token de acceso",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SpringDocConfiguration {

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components()
//                .addSecuritySchemes("bearer-key",
//                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
//    }
}
