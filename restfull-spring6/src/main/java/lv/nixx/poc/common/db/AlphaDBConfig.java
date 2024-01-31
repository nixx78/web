package lv.nixx.poc.common.db;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class AlphaDBConfig extends AbstractDBConfig {

    private static final String prefix = "alpha";

    public AlphaDBConfig(ApplicationContext context) {
        super(context, prefix);
    }

    @Bean(name = prefix + "DataSourceProperties")
    @ConfigurationProperties("db." + prefix)
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = prefix + "DataSource")
    public DataSource createSource() {
        return super.createSource();
    }

    @Bean(name = prefix + "EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return super.entityManagerFactory();
    }

    @Bean(name = prefix + "TransactionManager")
    public PlatformTransactionManager transactionManager() {
        return super.transactionManager();
    }
}
