package com.soccerfantasy.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

/**
 * Open APIs Configuration.
 * @author shalu
 */
@Configuration
@SecurityScheme(
        name = "jwtBearerToken",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @Info(title = "APIs for Soccer Fantasy Game", description = "This application lets football/soccer fans create fantasy"
        		+ " teams and sell or buy players", version = "v1"),
        security = @SecurityRequirement(name = "jwtBearerToken") 
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Soccer Fantasy")
                        .description("This application lets football/soccer fans create fantasy"
                        		+ " teams and sell or buy players")
                        .version("0.0.1")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact().name("Shalu Yadav").email("shaluforwork@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }
}
