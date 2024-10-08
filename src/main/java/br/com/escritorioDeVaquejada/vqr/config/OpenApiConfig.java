package br.com.escritorioDeVaquejada.vqr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Import({springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class})
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Vaquejada PRO")
                        .version("v1")
                        .description("Ticket management system and vaquejada finances")
                        .license(new License()
                                .name("Apache 2.0")));
    }

}
