package br.com.guilhermebehs.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				  .select()
				  .apis(RequestHandlerSelectors.basePackage("br.com.guilhermebehs"))
				  .paths(PathSelectors.any())
				  .build()
				  .apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo("RESTful API with Spring Boot", "My first RESTful API with Java And Spring Boot.",
				            "v1", "", new Contact("Guilherme Behs", "", ""), "", "", Collections.emptyList());
	}

}
