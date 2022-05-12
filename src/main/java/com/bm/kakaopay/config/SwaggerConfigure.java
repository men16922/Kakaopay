package com.bm.kakaopay.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 설정
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigure {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("KAKAOPAY 과제 1 - REST API")
                .version("1.0")
                .description("KAKAOPAY 과제 1 - REST API")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact().name("최병민").email("men2961@naver.com"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
