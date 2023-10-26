package com.project.moviebooking.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.annotations.AuthorizationScope;
import liquibase.hub.model.ApiKey;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author rohit
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements WebMvcConfigurer {

	public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.daynilgroup.mycity.mart";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
//				.securitySchemes(Arrays.asList(apiKey()))
//				.securityContexts(Collections.singletonList(securityContext()));
	}

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Mart Service API")
				.description("Mart Service API Documentation")
				.license("Aapche 2.0")
//                .licenseUrl("https://github.com/Codemiro/LICENCE/blob/master/LICENSE")
//                .contact(new Contact("Codemiro Technologies", "https://www.codemiro.com", "info@codemiro.com"))
				.build();
	}

//	private SecurityContext securityContext() {
//		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
//	}

//	private List<SecurityReference> defaultAuth() {
//		final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[] { authorizationScope };
//		return Collections.singletonList(new SecurityReference("Bearer Token", authorizationScopes));
//	}

	private ApiKey apiKey() {
//		return new ApiKey("Bearer Token", "Authorization", "header");
		return null;
	}

	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder()
				.operationsSorter(OperationsSorter.METHOD)
				.displayOperationId(true)
				.displayRequestDuration(true)
				.tagsSorter(TagsSorter.ALPHA)
				.build();
	}

}
