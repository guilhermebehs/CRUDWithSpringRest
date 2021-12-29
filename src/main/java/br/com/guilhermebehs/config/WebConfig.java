package br.com.guilhermebehs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		configurer
		.favorPathExtension(true)
	    .favorParameter(true)
	    .parameterName("mediaType")
	    .defaultContentType(MediaType.APPLICATION_JSON)
	    .mediaType("json", MediaType.APPLICATION_JSON)
	    .mediaType("xml", MediaType.APPLICATION_XML);
	}

}
