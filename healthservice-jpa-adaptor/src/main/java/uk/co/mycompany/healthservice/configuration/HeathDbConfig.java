package uk.co.mycompany.healthservice.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "defaultEntityManagerFactory",
    transactionManagerRef = "defaultTransactionManager", basePackages = {
    "uk.co.mycompany.healthservice.repository" })

public class HeathDbConfig {

    /**
     * Return the DataSource Object.
     * @return dataSource Object
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource defaultDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Persists the given EntityManagerFactory object.
     *
     * @param builder The entity manager factory builder object.
     * @param dataSource The default data source object.
     * @return The local container entity manager factory bean object.
     */
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean defaultEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                              @Qualifier("defaultDataSource")
                                                                                  DataSource dataSource) {

        HashMap<String, Object> properties = new HashMap<>();
        return builder.dataSource(dataSource).properties(properties)
            .packages("uk.co.mycompany.healthservice.domain")
            .persistenceUnit("doctorModel")
            .build();
    }

    /**
     * Persists the given PlatformTransactionManager object.
     * @param defaultEntityManagerFactory  EntityManagerFactory
     * @return The local platform Transaction Manager bean object
     */
    @Primary
    @Bean
    public PlatformTransactionManager defaultTransactionManager(
        @Qualifier("defaultEntityManagerFactory") EntityManagerFactory defaultEntityManagerFactory) {
        return new JpaTransactionManager(defaultEntityManagerFactory);
    }
}
