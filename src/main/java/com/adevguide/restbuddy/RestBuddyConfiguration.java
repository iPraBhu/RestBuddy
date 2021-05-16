package com.adevguide.restbuddy;

import static springfox.documentation.builders.PathSelectors.regex;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.adevguide.restbuddy.service.SelfFeignService;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author PraBhu
 *
 */
@Configuration
@EnableSwagger2
@Slf4j
public class RestBuddyConfiguration extends WebMvcConfigurationSupport {

	@Autowired
	SelfFeignService selfFeign;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*");
	}

	/* Swagger Config starts */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("RestBuddy API Documentation")
				.description("RestBuddy Documentation for all exposed endpoints").version("1.0.0")
				.license("Apache License Version 2.0")
				.contact(new Contact("PraBhu", "https://www.adevguide.com/contact-us", "prabhu.sites@gmail.com"))
				.build();
	}

	@Bean
	public Docket cityApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.adevguide.restbuddy.controller")).paths(regex("/city.*"))
				.build().apiInfo(metaData());

	}

	@Scheduled(cron = "* */25 * * * *")
	public void selfHit() {
		log.info("Self hitting at {}", LocalDateTime.now());
		selfFeign.selfHit();
	}

}
