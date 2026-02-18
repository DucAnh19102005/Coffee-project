package com.ducanh.coffee.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Coffee Project API") // Tiêu đề API của bạn
                        .version("1.0.0") // Phiên bản API
                        .description("API documentation for the Coffee Project application.") // Mô tả
                        .contact(new Contact()
                                .name("DucAnh") // Tên của bạn
                                .email("nguyenduyducanh19102005@gmail.com")) // Email của bạn
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }

}
