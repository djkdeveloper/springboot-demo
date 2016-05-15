package com.djk.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Date;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by dujinkai on 2016/5/15.
 * Swagger api 自动配置
 */
@Configuration
@EnableConfigurationProperties(ApiDocProperties.class)
@ConditionalOnClass(Docket.class)
public class ApiDocAutoConfiguration {

    /**
     * 注入Swagger api的配置文件实体
     */
    @Autowired
    private ApiDocProperties apiDocProperties;

    /**
     * 生成Docket 实体
     *
     * @return
     */
    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .genericModelSubstitutes(ResponseEntity.class)
                .directModelSubstitute(Date.class, String.class)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex(apiDocProperties.getIncludePattern()))
                .build()
                ;
    }

    /**
     * 获得ApiInfo 实体
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(apiDocProperties.getTitle(),
                apiDocProperties.getDescription(),
                apiDocProperties.getVersion(),
                apiDocProperties.getTermOfServiceUrl(),
                apiDocProperties.getContact(),
                apiDocProperties.getLicense(),
                apiDocProperties.getLicenseUrl());
    }
}
