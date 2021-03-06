package com.peng1m.education.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

//@Configuration
//@EnableSwagger2
//@Import({SpringDataRestConfiguration.class})
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket docket() {
        Tag userTag = new Tag("user", "Repository for User");
        Tag courseTag = new Tag("course", "Repository for Course");
        Tag examTag = new Tag("exam", "Repository for Examination");
        Tag markTag = new Tag("exam", "repository for Mark");
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(userTag, courseTag, examTag, markTag)
                .pathMapping("/")
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
}
