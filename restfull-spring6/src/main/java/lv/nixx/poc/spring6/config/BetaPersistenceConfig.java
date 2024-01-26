package lv.nixx.poc.spring6.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        basePackages = "lv.nixx.poc.spring6.repository.beta",
        entityManagerFactoryRef = "betaEntityManagerFactory",
        transactionManagerRef = "betaTransactionManager"
)
public class BetaPersistenceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.beta")
    public DataSource betaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean betaEntityManagerFactory(
            DataSource betaDataSource,
            @Value("${hibernate.hbm2ddl.auto}") String ddlAuto

    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(betaDataSource);
        em.setPackagesToScan("lv.nixx.poc.spring6.orm.beta");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager betaTransactionManager(LocalContainerEntityManagerFactoryBean alphaEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(alphaEntityManagerFactory.getObject()));
    }


}
