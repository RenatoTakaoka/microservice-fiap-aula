package com.github.rkkt.ms_pagamento.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
// Para endpoint protegido
// @SecurityScheme (name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer")
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microserviço de Pagamentos")
                        .version("v0.0.1")
                        .description("Projeto de Banana")
                        .termsOfService("https://www.youtube.com/")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/RenatoTakaoka/microservice-fiap-aula")
                        )
                );
    }

}