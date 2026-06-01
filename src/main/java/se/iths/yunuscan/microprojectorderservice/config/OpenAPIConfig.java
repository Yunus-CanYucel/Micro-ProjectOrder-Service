package se.iths.yunuscan.microprojectorderservice.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Order Service API")
                        .version("1.0")
                        .description("Order Service for E-commerce Platform\n\n"+
                                "## Authentication\n"+
                                "This API requires JWT Bearer token from Auth Server.\n\n"+
                                "### How to test:\n" +
                                "1. Call auth-server /auth/login to get token \n" +
                                "2. Click 'Authorize' button and enter: 'Bearer <your-token>'\n"+
                                "3. Then you can test the endpoints.")
                        .contact(new Contact()
                                .name("")
                                .email("")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter JWT token from auth-server")));
    }
}