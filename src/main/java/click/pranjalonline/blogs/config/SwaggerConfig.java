package click.pranjalonline.blogs.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER= "Authorization";

    private ApiKey getApiKey(){
        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
    }
    private ApiInfo getApiInfo(){
        return  new ApiInfo(
                "Blogs REST API",
                "Blogs API Documentation",
                "1.0.0",
                "",
                new Contact("Pranjal Das", "pranjalonline.click","pranjaldas525@gmail.com"),
                "NO LICENCE",
                "",
                Collections.emptyList()
        );
    }
    private SecurityContext getSecurityContext(){
        return SecurityContext.builder().securityReferences(getSecurityReference()).build();
    }
    private List<SecurityReference> getSecurityReference(){
        AuthorizationScope authorizationScope= new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes= new AuthorizationScope[1];
        authorizationScopes[0]= authorizationScope;
        return Arrays.asList(new SecurityReference("JWT",authorizationScopes));
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .securityContexts(Arrays.asList(getSecurityContext()))
                .securitySchemes(Arrays.asList(getApiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
