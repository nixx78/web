package lv.nixx.poc.spring6.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

import static lv.nixx.poc.spring6.config.DataSourceInit.initDB;

@Configuration
@EnableJpaRepositories(
        basePackages = "lv.nixx.poc.spring6.repository.alpha",
        entityManagerFactoryRef = "alphaEntityManagerFactory",
        transactionManagerRef = "alphaTransactionManager"
)
public class AlphaPersistenceConfig {


    //TODO Review, how is possible to work with properties: https://www.baeldung.com/configuration-properties-in-spring-boot: "5. Using @ConfigurationProperties on a @Bean Method"

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.alpha")
    public DataSource alphaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean alphaEntityManagerFactory(
            DataSource alphaDataSource,
            @Value("${spring.datasource.alpha.init-file}") String initFile,
            @Value("${hibernate.hbm2ddl.auto}") String ddlAuto
    ) throws SQLException {

        initDB(alphaDataSource, initFile);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(alphaDataSource);
        em.setPackagesToScan("lv.nixx.poc.spring6.orm.alpha");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager alphaTransactionManager(LocalContainerEntityManagerFactoryBean alphaEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(alphaEntityManagerFactory.getObject()));
    }


}
