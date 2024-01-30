package lv.nixx.poc.common.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class BetaDBConfig extends AbstractDBConfig {

    private static final String prefix = "beta";

    @Bean(name = prefix + "DataSourceProperties")
    @ConfigurationProperties("db." + prefix)
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = prefix + "DataSource")
    @ConfigurationProperties("db." + prefix + ".configuration")
    public DataSource createSource(@Value("${db." + prefix + ".init-file:#{null}}") String initFile) {
        return super.createSource(dataSourceProperties(), initFile);
    }


    @Bean(name = prefix + "EntityManagerFactory")
    @Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier(prefix + "DataSource") DataSource dataSource,
            @Value("${hibernate.hbm2ddl.auto}") String ddlAuto,
            @Value("${db." + prefix + ".packages.to.scan}") String packagesToScan

    ) throws SQLException {
        return super.entityManagerFactory(dataSource, ddlAuto, packagesToScan);
    }

    @Bean(name = prefix + "TransactionManager")
    @Override
    public PlatformTransactionManager transactionManager(@Qualifier(prefix + "EntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return super.transactionManager(entityManagerFactory);
    }
}
