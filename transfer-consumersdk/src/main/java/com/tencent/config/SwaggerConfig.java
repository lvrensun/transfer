package com.tencent.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 * */
@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig{

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tencent.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        log.info("Swagger接口文档地址： http://192.168.4.4:8882/swagger-ui.html");
        return new ApiInfoBuilder()
                .title("壁虎企业微信会话内容存档 RESTful API 文档")
                .description("文档描述：")
                .termsOfServiceUrl("192.168.4.4:8882/")
                .contact(new Contact(":lvshiyang","","lvshiyang@supplyfintech.com"))
                .version("1.0")
                .build();
    }

}