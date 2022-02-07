package uk.co.mycompany.healthservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import uk.co.mycompany.healthservice.service.HealthService;
import uk.co.mycompany.healthservice.service.impl.LocalHealthService;
import uk.co.mycompany.healthservice.strategy.impl.HealthServiceDefaultStrategy;

@Configuration
@ComponentScan(basePackages = {"uk.co.mycompany.healthservice"})
@PropertySource("classpath:healthservice-host.properties")
public class HealthServiceAppConfiguration {

    @Value("${spring.application.name}")
    private String serviceName;


    @Autowired
    private HealthServiceDefaultStrategy healthServiceDefaultStrategy;

    /**
     * Creates a bean for the Local Health Service.
     *
     * @return a Abstract Local Health Service Bean
     */
    @Bean
    public HealthService consumerAggregateLocalService() {
        LocalHealthService localHealthService = new LocalHealthService();
        localHealthService.addStrategy(healthServiceDefaultStrategy);
        return localHealthService;
    }

}
