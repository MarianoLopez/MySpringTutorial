package com.z.bookbackend.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class Swagger {
    val metaData = ApiInfo("API Docs", "REST API", "1.0", "Terms of service", Contact("Mariano Lopez", "-", "m_villa@hotmail.com"), "", "https://www.apache.org/licenses/LICENSE-2.0")
    val basePackage = "com.z.bookbackend"

    @Bean fun docket(): Docket = Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("$basePackage.controllers"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(metaData)
}