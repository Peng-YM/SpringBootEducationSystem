package com.peng1m.education.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

//@Configuration
//@EnableSwagger2
//@Import({SpringDataRestConfiguration.class})
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket docket(){
        Tag userTag = new Tag("user", "Repository for User");
        Tag courseTag = new Tag("course", "Repository for Course");
        Tag examTag = new Tag("exam", "Repository for Examination");
        Tag markTag = new Tag("exam", "repository for Mark");
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(userTag, courseTag, examTag, markTag)
                .pathMapping("/api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.peng1m.education.repository"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "REST API",
                "Education System API",
                "API",
                "Terms of service",
                new Contact("", "https://github.com/Peng-YM", ""),
                "License of API", "https://github.com/Peng-YM", Collections.emptyList());
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
