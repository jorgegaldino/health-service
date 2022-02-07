/*
 *   File      : ConsumerAggregateAppConfiguration.java
 *   Author    : Sara.Figueiredo
 *   Copyright : Collinson Group Limited (2019)
 *   Created   : 21-Oct-2019
 *
 *   History
 *     21-Oct-2019 Sara.Figueiredo The initial version.
 */

package uk.co.mycompany.healthservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import uk.co.mycompany.healthservice.service.HealthService;
import uk.co.mycompany.healthservice.service.impl.HeathLocalService;
import uk.co.mycompany.healthservice.strategy.impl.HealthServiceDefaultStrategy;

import java.util.List;

@Configuration
@ComponentScan(basePackages = {"uk.co.mycompany.healthservice"})
@PropertySource("classpath:healthservice-host.properties")
public class HealthServiceAppConfiguration {

    @Value("${spring.application.name}")
    private String serviceName;


    @Autowired
    private HealthServiceDefaultStrategy healthServiceDefaultStrategy;

    /**
     * Creates a bean for the Consumer Aggregate Local Service.
     *
     * @return a Abstract Consumer Aggregate Service Bean
     */
    @Bean
    public HealthService consumerAggregateLocalService() {
        HeathLocalService healthLocalService = new HeathLocalService();
        healthLocalService.addStrategy(healthServiceDefaultStrategy);
        return healthLocalService;
    }

}
