package lv.nixx.poc.spring6.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

import static lv.nixx.poc.spring6.config.DataSourceInit.initDB;

@Configuration
@EnableJpaRepositories(basePackages = "lv.nixx.poc.spring6.repository.alpha", entityManagerFactoryRef = "alphaEntityManagerFactory")
public class AlphaPersistenceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.alpha")
    public DataSource alphaDataSource() {
        DataSource ds = DataSourceBuilder.create().build();
        return ds;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean alphaEntityManagerFactory(DataSource alphaDataSource, @Value("${spring.datasource.alpha.init-file}") String initFile) throws SQLException {
        initDB(alphaDataSource, initFile);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(alphaDataSource);
        em.setPackagesToScan("lv.nixx.poc.spring6.orm.alpha");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        em.setJpaPropertyMap(properties);

        return em;
    }


}
