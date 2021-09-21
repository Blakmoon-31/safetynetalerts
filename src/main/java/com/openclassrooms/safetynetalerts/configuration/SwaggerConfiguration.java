package com.openclassrooms.safetynetalerts.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	public static final String PERSONS_TAG = "Persons controller";
	public static final String FIRE_STATION_TAG = "Fire stations controller";
	public static final String MEDICAL_RECORDS_TAG = "Medical records controller";
	public static final String SAFETYNET_ALERTS_TAG = "SafetyNet Alerts controller";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.openclassrooms.safetynetalerts.controller"))
				.paths(PathSelectors.any()).build()
				.tags(new Tag(PERSONS_TAG, "API for CRUD operations on persons list"),
						new Tag(FIRE_STATION_TAG, "API for CRUD operations on fire stations mapping list"),
						new Tag(MEDICAL_RECORDS_TAG, "API for CRUD operations on medical records list"),
						new Tag(SAFETYNET_ALERTS_TAG, "API for getting lists from SafetyNet Alerts"));
	}
}
