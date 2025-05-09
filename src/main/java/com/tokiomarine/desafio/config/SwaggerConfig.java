package com.tokiomarine.desafio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    /**
     * Configuração do Swagger para a API.
     *
     * @return OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
            .info(new Info()
                .title("Desafio Tokio marine")
                .description("API para agendamento de transferências")
                .version("1.0.0"));

    }
}
