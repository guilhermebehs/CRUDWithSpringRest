package br.com.guilhermebehs.config;

import java.util.Collections;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customApi() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API with Java 16 and Spring Boot 2.6.2")
						.version("v1")
						.description("My first RESTful API with Java And Spring Boot")
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
