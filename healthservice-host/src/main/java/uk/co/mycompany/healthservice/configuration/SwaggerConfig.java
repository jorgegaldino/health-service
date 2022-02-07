package uk.co.mycompany.healthservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SwaggerConfig {

    /**
     * Expose the swagger documentation for the implemented APIs.
     * @param environment the spring environment context.
     * @return the configured swagger activation.
     */
    @Bean
    public OpenAPI consumerAggregateApi(Environment environment) {
        return new OpenAPI()
            .info(new Info().title(environment.getRequiredProperty("healthservice.swagger.apiTitle", String.class))
                .description(environment.getRequiredProperty("healthservice.swagger.apiDescription", String.class))
                .version(environment.getRequiredProperty("healthservice.swagger.apiVersion", String.class))
                .contact(new Contact()
                    .name(environment.getRequiredProperty("healthservice.swagger.contactName", String.class))
                    .url(environment.getRequiredProperty("healthservice.swagger.contactUrl", String.class))
                    .email(environment.getRequiredProperty("healthservice.swagger.contactEmail", String.class))
                ));
    }
}

