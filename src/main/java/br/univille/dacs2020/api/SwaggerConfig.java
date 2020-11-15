package br.univille.dacs2020.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api2() { 
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("br.univille.dacs2020.api"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())
        .securitySchemes(Arrays.asList(apiKey()))
        .securityContexts(Arrays.asList(securityContext()));       
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("REST API")
        .description("Exemplo de Documentação da API.").termsOfServiceUrl("")
        .contact(new Contact("UNIVILLE", "", "univille@univille.br"))
        .license("Apache License Version 2.0")
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
        .version("0.0.1")
        .build();
    }
    private ApiKey apiKey() {
        return new ApiKey("apiKey", "Authorization", "header");
    }
    private SecurityContext securityContext() {
        return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.regex("/api/v1.*"))
        .build();
    }
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("apiKey", authorizationScopes));
    }
    @Bean
    public SecurityConfiguration security() {
            return SecurityConfigurationBuilder.builder()
                    .clientId("test-app-client-id")
                    .clientSecret("test-app-client-secret")
                    .realm("test-app-realm")
                    .appName("test-app")
                    .scopeSeparator(",")
                    .additionalQueryStringParams(null)
                    .useBasicAuthenticationWithAccessCodeGrant(false)
                    .build();
    }
    
}
