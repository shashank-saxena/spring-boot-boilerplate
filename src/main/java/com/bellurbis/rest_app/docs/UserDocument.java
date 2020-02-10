package com.bellurbis.rest_app.docs;

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
public class UserDocument {
	@Bean
	public Docket DevSwaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/user/*"))
				.apis(RequestHandlerSelectors.basePackage("com.bellurbis.rest_app.controllers"))
				.build()
				.apiInfo(apiDetails());
	}
	private ApiInfo apiDetails()
	{
		return new ApiInfo
		("rest_app project documentation",//title of document
		"exploring rest apis and swagger",//description
		"1.0",//version of project
		"don't try to open. it is malicious.",//terms of the apis
		new Contact("Suman Maity", "http://bellurbis.com","suman.maity@bellurbis.com"),//contact details of the Owner (name,website,email)
		"Licence of bellurbis",//licence of the project
		"http://bellurbis.com/contact-us/",//licence url
		 Collections.emptyList());//Vendors
	}
}
