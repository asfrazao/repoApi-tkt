package br.com.citrus.ticket.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Citrus Cloud Ticket API")
                        .description("powered by Digivox")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Nivaldo Montenegro Junior")
                                .url("https://digivox.com.br/")
                                .email("junior@digivox.com.br")
                        )
                );
    }
}
