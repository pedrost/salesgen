package co.boldtec.salesgen.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**") // This will document all the endpoints under "/api"
                .build();
    }

    @Bean
    public Info apiInfo() {
        return new Info()
                .title("SalesGen API")
                .description("API documentation for SalesGen application")
                .version("1.0")
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"));
    }
}
