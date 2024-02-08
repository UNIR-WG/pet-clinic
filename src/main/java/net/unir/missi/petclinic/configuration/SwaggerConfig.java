package net.unir.missi.petclinic.configuration;

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

// http://localhost:8080/swagger-ui/

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("net.unir.missi.petclinic.controller"))
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo()).useDefaultResponseMessages(false);
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("PetClinic Sample", "PetClinic Sample API Description", "1.0", "https://www.PetClinic.es",
				new Contact("PetClinic", "https://www.PetClinic.es", "info@PetClinic.es"),
				"Apache License, Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}

}
