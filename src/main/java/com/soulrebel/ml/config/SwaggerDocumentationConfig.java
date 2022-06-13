package com.soulrebel.ml.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerDocumentationConfig {

    private static final String EMAIL = "jonathanhinestroza87@gmail.com";

    private static final String NAME = "Jonathan Hinestroza";
    private static final String URL = "https://github.com/SoulMan87";
    private static final String BASE_PACKAGE = "com.soulrebel.ml.controller";

    private Api api;

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE)).build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(api.getTitle())
                .description(api.getDescription())
                .version(api.getVersion())
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .contact(new Contact(NAME, URL, EMAIL)).build();
    }

    @Getter
    @Setter
    public static class Api {

        private String title;
        private String version;
        private String description;
    }
}


