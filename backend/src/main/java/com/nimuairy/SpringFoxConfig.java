package com.nimuairy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@Profile("dev")
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(createApiInfo());
	}

	private ApiInfo createApiInfo() {
		return new ApiInfo("Nimuairy API",
				"Nimuairy API documentation",
				"1.00",
				"urn:tos",
				new Contact("Paweł", "", ""),
				"my own licence",
				"licenceUrl",
				Collections.emptyList());
	}
}